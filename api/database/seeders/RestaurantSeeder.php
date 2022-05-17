<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;

class RestaurantSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        for ($i = 0; $i < 30; $i++) {
            DB::table('restaurants')->insert([
                'name' => Str::random(10),
                'description' => Str::random(100),
                'localization' => Str::random(20),
                'phone_number' => Str::random(10),
                'website' => Str::random(50),
                'hours' => random_int(0,24),
                'user_id' => random_int(1,30)
            ]);
        }
    }
}
