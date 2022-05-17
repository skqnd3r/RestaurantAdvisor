<?php

namespace Database\Seeders;

use App\Models\Restaurant;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;
use App\Traits\GradesCalculator;


class GradeSeeder extends Seeder
{
    use GradesCalculator;

    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        for ($i = 1; $i < 31; $i++) {
            $r = random_int(5, 7);
            for ($j = 0; $j < $r; $j++) {
                if ($j < 3) {
                    $com = null;
                } else {
                    $com = Str::random(100);
                }
                
                DB::table('grades')->insert([
                    'value' => random_int(1, 5),
                    'restaurant_id' => $i,
                    'user_id' => random_int(1, 30),
                    'comment' => $com
                ]);

                $this->update_restaurant_grade($i);
            }
        }
    }
}