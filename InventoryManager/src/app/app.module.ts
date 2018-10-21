import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { from } from 'rxjs';
import { UpdateDBComponent } from './update-db/update-db.component';

const routes: Routes = [
  { path: 'nav-bar', component: NavBarComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'updateDB', component: UpdateDBComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    DashboardComponent,
    UpdateDBComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
