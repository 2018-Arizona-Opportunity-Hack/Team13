import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {ChartsModule} from 'ng2-charts/ng2-charts'
import { AppComponent } from './app.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import {FormsModule} from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';
import {AppService} from './app.service';
import {MatDatepickerModule,MatFormFieldModule,MatNativeDateModule,MatInputModule} from '@angular/material'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';

import { NavBarComponent } from './nav-bar/nav-bar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { from } from 'rxjs';
import { UpdateDBComponent } from './update-db/update-db.component';
import { UploadModule } from './upload/upload.module';
import { MatCardModule } from '@angular/material';

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
    ChartsModule,
    NgMultiSelectDropDownModule.forRoot(),
    FormsModule,
    HttpClientModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,BrowserAnimationsModule,
    UploadModule,
    MatCardModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }
