import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyLinesComponent } from './company-lines.component';

describe('CompanyLinesComponent', () => {
  let component: CompanyLinesComponent;
  let fixture: ComponentFixture<CompanyLinesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompanyLinesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanyLinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
