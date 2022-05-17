<?php

namespace App\Traits;

use Illuminate\Support\Facades\DB;
use App\Models\Restaurant;
use App\Models\Grade;

/*
|--------------------------------------------------------------------------
| Grades Calculator Trait
|--------------------------------------------------------------------------
|
| This trait will be used for calculations relative to restaurants grades.
|
*/

trait GradesCalculator
{
	/**
     * Return the arithmetic mean of all the grades of a restaurant.
     *
     * @param  integer  $rid
     * @return float $mean
     */
	protected function update_restaurant_grade($rid)
	{
		$restaurant = Restaurant::find($rid);
		$grades = $restaurant->grades;
		$mean = 0;
		foreach ($grades as $grade){
			$mean += $grade->value;
		}
		$mean = round($mean / count($grades), 1);
		$restaurant->update(['grade' => $mean]);
		return;
	}
}