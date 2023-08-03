import { NgModule } from '@angular/core';
import { SharedModule } from '../shared';
import { AboutComponent, HomeComponent } from './pages';

@NgModule({
  imports: [SharedModule],
  providers: [],
  declarations: [AboutComponent, HomeComponent],
})
export class HomeModule {}
