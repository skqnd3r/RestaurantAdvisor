<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\UserController;
use App\Http\Controllers\RestaurantController;
use App\Http\Controllers\MenuController;
use App\Http\Controllers\GradeController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});


// _________ SHOW

Route::get('/users', [UserController::class, 'show']);

Route::get('/restaurants', [RestaurantController::class, 'show_restaurants']);

Route::get('/restaurant/{id}/menus', [MenuController::class, 'show_menus']);

Route::get('/restaurant/{id}/grades', [GradeController::class, 'show_grades']);


// _________ USER

Route::post('/register', [UserController::class, 'register']);

Route::post('/auth', [UserController::class, 'authenticate']);


Route::group(['middleware' => ['auth:sanctum']], function () {
    
    Route::delete('/user', [UserController::class, 'delete']);
    
    Route::get('/user/restaurants', [UserController::class, 'show_user_restaurants']);
    
    Route::get('/user/grades', [UserController::class, 'show_user_grades']);
    
    Route::put('/user', [UserController::class, 'modify']);
    
    // _________ RESTAURANT
    
    Route::post('/restaurant', [RestaurantController::class, 'add']);
    
    
    Route::put('/restaurant/{id}', [RestaurantController::class, 'modify']);
    
    Route::delete('/restaurant/{id}', [RestaurantController::class, 'delete']);
    
    // _________ MENU
    
    Route::post('/restaurant/{id}/menu', [MenuController::class, 'add_menu']);
    
    Route::put('/restaurant/{id}/menu/{menu_id}', [MenuController::class, 'modify_menu']);
    
    Route::delete('/restaurant/{id}/menu/{menu_id}', [MenuController::class, 'delete_menu']);
    
    // _________ GRADE
    
    Route::post('/restaurant/{id}/grade', [GradeController::class, 'add']);
    
    Route::put('/grade/{id}', [GradeController::class, 'modify']);
    
    Route::delete('/grade/{id}', [GradeController::class, 'delete']);
    
    Route::delete('/grade/{id}/comment', [GradeController::class, 'delete_comment']);

});

// _________ IMAGES

Route::get('/restaurant/image/{filename}', [RestaurantController::class, 'show_image']);