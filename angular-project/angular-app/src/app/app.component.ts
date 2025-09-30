import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { ProductComponent } from './products/components/product/product.component';

@Component({
  selector: 'app-root',
  imports: [CommonModule, ProductComponent ,RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title: string = 'Hello World Angular Project';
  enabled: boolean = true;

  courses: string[] = ['Java', 'Spring', 'Hibernate', 'JavaScript', 'TypeScript', 'Angular', 'React', 'Vue'];
  setEnabled(): void {
    this.enabled = !this.enabled;
  }
}
