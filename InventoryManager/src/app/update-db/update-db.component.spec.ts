import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDBComponent } from './update-db.component';

describe('UpdateDBComponent', () => {
  let component: UpdateDBComponent;
  let fixture: ComponentFixture<UpdateDBComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateDBComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateDBComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
