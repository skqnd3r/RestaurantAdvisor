<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Validator;
use App\Models\User;
use App\Models\Restaurant;
use App\Models\Grade;
use App\Traits\ApiResponser;
use App\Traits\GradesCalculator;

class GradeController extends Controller
{
    use ApiResponser, GradesCalculator;

    public function show_grades($rid)
    {
        $restaurant = Restaurant::find($rid);
        if ($restaurant == null) {
            return $this->error(404);
        }
        $grades = $restaurant->grades;
        //retrieve name of user
        foreach ($grades as $grade) {
            $grade['user_id'] = $grade->user['username'];
            unset($grade->user);
        }
        //change response
        return response()->json($grades, 200);
    }

    // public function
    public function add($rid, Request $request)
    {
        $restaurant = Restaurant::find($rid);
        if ($restaurant == null) {
            return $this->error(404);
        }
        $user = auth()->user();
        $grades = $user->grades;
        foreach ($grades as $g) {
            if ($g->restaurant_id == $rid) {
                return $this->error(409);
            }
        }
        $val = Validator::make($request->all(), [
            'value' => 'required|integer|min:0|max:5',
            'comment' => 'nullable|max:255'
        ]);
        if ($val->fails()) {
            $errors = $this->error_messages($val->failed());
            return $this->error(400, $errors);
        }
        $data = $request->all();
        $data['user_id'] = $user->id;
        $data['restaurant_id'] = $restaurant->id;
        $grade = Grade::create($data);
        $this->update_restaurant_grade($rid);
        return $this->success($grade, 201);
    }

    public function modify($id, Request $request)
    {
        $grade = Grade::find($id);
        if ($grade == null) {
            return $this->error(404);
        }
        $val = Validator::make($request->all(), [
            'value' => 'required|integer|min:0|max:5',
            'comment' => 'nullable|max:255'
        ]);
        if ($val->fails()) {
            $errors = $this->error_messages($val->failed());
            return $this->error(400, $errors);
        }
        $grade->value = $request->value;
        if ($request->comment) {
            $grade->comment = $request->comment;
        }
        $grade->save();
        $this->update_restaurant_grade($grade->restaurant->id);
        return $this->success($grade, 201);
    }

    public function delete($id)
    {
        $grade = Grade::find($id);
        if ($grade == null) {
            return $this->error(404);
        }
        $rid = $grade->restaurant->id;
        Grade::destroy($id);
        $this->update_restaurant_grade($rid);
        return $this->success(null, 200);
    }

    public function delete_comment($id)
    {
        $grade = Grade::find($id);
        $grade->comment = null;
        $grade->save;
        return $this->success($grade, 200);
    }
}
