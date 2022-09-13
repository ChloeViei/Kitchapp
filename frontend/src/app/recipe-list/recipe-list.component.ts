import { Component, OnInit } from '@angular/core';
import { Recipe } from '../recipe';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipes: Recipe[];

  constructor() { }

  ngOnInit(): void {

    this.recipes = [
    {
      "id": 1,
      "title": "bolognaise",
      "description": "lorem ispum",
      "instruction": "faire cuire et manger",
      "difficulty": 2
    },
    {
      "id": 2,
      "title": "carotte",
      "description": "lorem ispum",
      "instruction": "faire cuire et manger",
      "difficulty": 3
    }]
  }

}
