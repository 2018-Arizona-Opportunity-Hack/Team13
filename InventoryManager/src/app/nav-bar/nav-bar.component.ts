import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  isNavbarCollapsed=true;

  constructor(private router: Router) { }

  ngOnInit() {
    this.dashBoardTapped();
  }

  dashBoardTapped(){
    this.router.navigateByUrl('/dashboard');
  }

}
