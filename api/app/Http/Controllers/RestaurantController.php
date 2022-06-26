<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Arr;
use App\Models\Restaurant;
use App\Traits\ApiResponser;
use Illuminate\Support\Facades\Response;


class RestaurantController extends Controller
{
    use ApiResponser;
    
    public function show_restaurants()
    {
        $restaurants = Restaurant::all();
        return response()->json($restaurants, 200);
    }

    public function add(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'name' => 'required|unique:restaurants,name',
            'description' => 'required',
            'localization' => 'required',
            'phone_number' => 'required',
            'website' => 'required',
            'hours' => 'required',
        ]);

        if ($validator->fails()) {
            $errors = $this->error_messages($validator->failed());
            return $this->error(400,$errors);
        }
        $data = $request->all();
        $data['user_id'] = auth()->user()->id;
        $restaurant = Restaurant::create($data);
        return response()->json($restaurant, 201);
    }

    public function modify($id, Request $request)
    {
        $validator = Validator::make($request->all(), [
            'name' => 'unique:restaurants,name',
        ]);

        if ($validator->fails()){
            $errors = $this->error_messages($validator->failed());
            return $this->error(400,$errors);
        }

        $restaurant = Restaurant::find($id);
        $req = Arr::except($request, ['_token', '_method']);
        foreach ($req->keys() as $key) {
            if ($request->$key != NULL) {
                $restaurant->update([$key => $request->$key]);
            }
        }
        return $this->success($restaurant, 200);
    }

    public function delete($id)
    {
        $restaurant = Restaurant::find($id);
        if ($restaurant == NULL) {
            return response(400);
        }
        $restaurant->delete();
        return response()->json(200);
    }

    public function show_image($filename)
    {
        $path = 'storage/'.$filename;
        // dd($path);
        return response()->file($path);
    }
}