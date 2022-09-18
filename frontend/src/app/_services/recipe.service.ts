import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recipe } from '../recipe';

const API_URL = 'http://localhost:8080/api//v1/recipes';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(private httpClient: HttpClient) { }

  getRecipesList(): Observable<Recipe[]> {
    console.log(this.httpClient.get<Recipe[]>(API_URL));
    return this.httpClient.get<Recipe[]>(API_URL);
  }

}
