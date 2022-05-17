<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;
use Illuminate\Support\Facades\Hash;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('users')->insert([
            'username' => "Admin",
            'name' => "Str::random(10)",
            'firstname' => "Brandon",
            'email' => "oui@gmail.com",
            'age' => "69",
            'password' => Hash::make('password'),
        ]);

        for ($i = 0; $i < 29; $i++) {
            DB::table('users')->insert([
                'username' => Str::random(10),
                'name' => Str::random(10),
                'firstname' => Str::random(10),
                'email' => Str::random(10).'@gmail.com',
                'age' => random_int(0,99),
                'password' => Hash::make('password'),
            ]);
        }
    }
}
