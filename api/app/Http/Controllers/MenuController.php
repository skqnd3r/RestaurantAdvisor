<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use App\Traits\ApiResponser;
use Illuminate\Support\Arr;
use App\Models\Menu;
use App\Models\Restaurant;

class MenuController extends Controller
{
    use ApiResponser;

    public function show_menus($rid)
    {
        $restaurant = Restaurant::find($rid);
        $menus = $restaurant->menus;
        return response()->json($menus, 200);
    }

    public function add_menu($id, Request $request)
    {
        if (($restaurant = Restaurant::find($id))== null){
            return $this->error(400);
        }

        $data = $request->all();
        $data['restaurant_id'] = $restaurant->id;
        $validator = Validator::make($data, [
            'name' => 'required|unique:menus,name',
            'restaurant_id' => 'required|int',
            'description' => 'required',
            'price' => 'required|numeric',
        ]);
    
        if ($validator->fails()){
            $errors = $this->error_messages($validator->failed());
            return response()->json($errors, 400);
        }
        $menu = Menu::create($data);
        return response()->json($menu, 201);
    }

    public function modify_menu($mid, Request $request)
    {
        $menu = Menu::find($mid);
        if ($menu == NULL){
            return $this->error(400);
        }

        $validator = Validator::make($request->all(), [
            'name' => 'unique:menus,name',
        ]);
        
        if ($validator->fails()){
            $errors = $this->error_messages($validator->failed());
            return $this->error(400, $errors);
        }

        $req = Arr::except($request, ['_token', '_method','register_id']);
        foreach (array_keys($req) as $key) {
            if ($request->$key != NULL) {
                $menu->update([$key => $request->$key]);
            }
        }
        return  $this->success($menu, 201);
    }

    public function delete_menu($id, $mid)
    {   
        if ((Restaurant::find($id)) == null){
            return $this->error(400);
        }
        $menu = Menu::find($mid);
        if ($menu == NULL) {
            return $this->error(400);
        }
        $menu->delete();
        return $this->success($menu, 200);
    }
}