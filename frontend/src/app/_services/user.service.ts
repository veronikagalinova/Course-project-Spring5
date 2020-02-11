﻿import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { User } from '@app/_models/User';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<User[]>(`${environment.apiUrl}/api/users`);
    }

    getById(id: string) {
        return this.http.get<User>(`${environment.apiUrl}/api/users/${id}`);
    }

    register(user: User) {
        return this.http.post<User>(`${environment.apiUrl}/api/users`, user);
    }

    updateProfile(user: User) {
        return this.http.put<User>(`${environment.apiUrl}/api/users/${user.id}`, user);
    }
}