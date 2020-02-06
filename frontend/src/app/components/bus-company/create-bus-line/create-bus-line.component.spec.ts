import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateBusLineComponent } from './create-bus-line.component';

describe('CreateBusLineComponent', () => {
  let component: CreateBusLineComponent;
  let fixture: ComponentFixture<CreateBusLineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateBusLineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateBusLineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
