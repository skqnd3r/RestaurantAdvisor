<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Arr;
use App\Models\User;
use App\Traits\ApiResponser;

class UserController extends Controller
{
    use ApiResponser;

    public function register(Request $request) {
        $validator = Validator::make($request->all(), [
            'login' => 'required|min:2|max:32|unique:users,username',
            'name' => 'required|min:2|max:32',
            'firstname' => 'required|min:2|max:32',
            'password' => 'required|min:6|max:32',
            'age' => 'required|integer',
            'email' => 'required|email',
        ]);
        
        if ($validator->fails()) {
            $errors = $this->error_messages($validator->failed());
            return response()->json($errors, 400);
        }
        $data = $request->all();
        $data['username'] = $data['login'];
        $data['password'] = Hash::make($request->password);
        unset($data['login']);
        $user = User::create($data);
        return response()->json($user, 201);
    }

    public function authenticate(Request $request) {
        $validator = Validator::make($request->all(), [
            'login' => 'required',
            'password' => 'required',
        ]);

        if($validator->fails()) {
            return response()->json("Login ou mot de passe incorrect.", 400);
        }
        $credentials = $request->all();
        $credentials['username'] = $credentials['login'];
        unset($credentials['login']);
        if (Auth::attempt($credentials)) {
            $token = auth()->user()->createToken('auth', ['modify_user']);
            $token = preg_replace('/.*[|]/', '', $token->plainTextToken);
            $user = auth()->user();
            return response()->json(['token' => $token, 'user' => $user], 200);
        }
        return response()->json("Login ou mot de passe incorrect.", 400);
    }

    public function show(Request $request) {
        $users = User::all();
        return response()->json($users, 200);
    }

    public function show_user_restaurants() {
        return response()->json(auth()->user()->restaurants, 200);
    }

    public function show_user_grades() {
        $grades = auth()->user()->grades;
        foreach($grades as $grade){
            $grade['user_id'] = $grade->restaurant['name'];
            unset($grade->restaurant);
        }
        return response()->json($grades , 200);
    }

    public function modify(Request $request) {
        
        $validator = Validator::make($request->all(), [
            'name' => 'nullable|min:2|max:32',
            'firstname' => 'nullable|min:2|max:32',
            'age' => 'nullable|integer',
            'email' => 'nullable|email',
        ]);

        if ($validator->fails()){
            $errors = $this->error_messages($validator->failed());
            return response()->json($errors, 400);
        }

        $user = User::find(auth()->user()->id);
        $req = Arr::except($request, ['login', 'password']);
        foreach ($req->keys() as $key) {
            if ($request->$key != NULL) {
                $user->update([$key => $request->$key]);
            }
        }
        return response()->json($user, 200);
    }

    public function delete(Request $request) {
        $user = User::find(auth()->user()->id);
        $user->delete();
        return response()->json(200);
    }

    public function show_register() {               // FOR TESTING ? 
        return csrf_token();
        return view('user/register');
    }
    
    public function show_auth() {                   // FOR TESTING
        return view('user/auth');
    }

    public function disconnect(Request $request) {  // FOR TESTING
        auth()->user()->tokens()->delete();
        return response()->json("Vous avez été déconnecté.", 200);
    }
}