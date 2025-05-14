import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-square',
  standalone: false,
  templateUrl: './square.component.html',
  styleUrl: './square.component.css'
})
export class SquareComponent {

  @Input() value!: 'X' | 'O';
  
}
