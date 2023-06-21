import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { FooterComponent } from './core/layout/footer.component';
import { HeaderComponent } from './core/layout/header.component';
import { TableComponent } from './features/table/table.component';

@NgModule({
  imports: [BrowserModule, HeaderComponent, FooterComponent, FormsModule],
  declarations: [AppComponent, TableComponent],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
