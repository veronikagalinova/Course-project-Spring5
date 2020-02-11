import { Component, OnInit } from '@angular/core';
import { UserService } from '@app/_services/user.service';
import { User } from '@app/_models/User';
import { MatTableDataSource, MatSnackBar, MatDialog } from '@angular/material';
import { UsersDialogComponent } from '../users-dialog/users-dialog.component';
import { NotificationService } from '@app/_services/error/notification.service';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.css']
})
export class ManageUsersComponent implements OnInit {

  users: User[];

  dataSource = new MatTableDataSource<User>();

  displayedColumns: string[] = ['username', 'firstName', 'lastName',
    'roles', 'action'];

  constructor(private usersService: UserService,
    private notificationService: NotificationService,
    public dialog: MatDialog) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.usersService.getAll().subscribe(res => {
      this.users = res;
      this.dataSource.data = res;
    });
  }

  openDialog(action: string, user: User) {
    let dialogRef = this.dialog.open(UsersDialogComponent, {
      width: '600px',
      data: {
        action: action,
        user: user
      }
    });

    dialogRef.componentInstance.event.subscribe((result) => {
      if (result.event == 'Add') {
        this.addUser(result.user);
      } else if (result.event == 'Update') {
        this.updateUser(result.user);
      } else if (result.event == 'Delete') {
        this.deleteUser(result.user);
      }
    });
  }

  addUser(user: User) {
    this.usersService.register(user).subscribe(res => {
      this.getUsers();
      this.notificationService.showSuccess(`User ${user.username} has been successfully created.`);
    });
  }

  updateUser(user: User) {
    this.usersService.updateProfile(user.id, user).subscribe(res => {
      this.getUsers();
      this.notificationService.showSuccess(`User ${user.username} has been successfully updated.`)
    });
  }

  deleteUser(user: User) {
    this.usersService.deleteProfile(user.id).subscribe(res => {
      this.getUsers();
      this.notificationService.showSuccess(`User ${user.username} has been deleted.`)
    });
  }

}
