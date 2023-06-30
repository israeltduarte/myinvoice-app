import { NgModule } from '@angular/core';
import { HomeRoutingModule } from './home-routing.module';

import { AboutComponent, HomeComponent } from './pages';

import { SharedModule } from '../shared';

@NgModule({
  imports: [HomeRoutingModule, SharedModule],
  providers: [],
  declarations: [AboutComponent, HomeComponent],
})
export class HomeModule {}
