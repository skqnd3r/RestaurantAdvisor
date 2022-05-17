<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;

class MenuSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        for ($i = 1; $i < 31; $i++) {
            $r = random_int(3, 6);
            for ($j = 0; $j < $r; $j++) {
                DB::table('menus')->insert([
                    'restaurant_id' => $i,
                    'name' => Str::random(10),
                    'description' => Str::random(100),
                    'price' => random_int(0, 20)
                    // add image
                ]);
            }
        }
    }
}
