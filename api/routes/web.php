<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\UserController;
use App\Http\Controllers\RestaurantController;
use App\Http\Controllers\MenuController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::post('/register', [UserController::class, 'register']);

Route::get('/register', [UserController::class, 'show_register']);

Route::get('/disconnect', [UserController::class, 'disconnect']);

Route::post('/auth', [UserController::class, 'authenticate']);

Route::get('/auth', [UserController::class, 'show_auth']);

Route::get('/users', [UserController::class, 'show']);

// _________

Route::get('/restaurants', [RestaurantController::class, 'show']);

Route::post('/restaurant', [RestaurantController::class, 'add']);

Route::put('/restaurant/{id}', [RestaurantController::class, 'modify']);

Route::delete('/restaurant/{id}', [RestaurantController::class, 'delete']);

// _________

Route::get('/restaurant/{id}/menus/{mid}', [MenuController::class, 'show_menu']);

Route::post('/restaurant/{id}/menu', [MenuController::class, 'add_menu']);

Route::put('/restaurant/{id}/menu/{menu_id}', [MenuController::class, 'modify_menu']);

Route::delete('/restaurant/{id}/menu/{menu_id}', [MenuController::class, 'delete_menu']);

// _________

Route::get('/register', [UserController::class, 'show_register']);    // test route ? Token csrf sur mobile ?

Route::get('/disconnect', [UserController::class, 'disconnect']);     // test route

Route::get('/auth', [UserController::class, 'show_auth']);            // test route