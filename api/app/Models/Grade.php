<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use App\Models\User;
use App\Models\Restaurant;

class Grade extends Model
{
    use HasFactory;
    
    protected $fillable = [
        'value',
        'user_id',
        'restaurant_id',
        'comment',
    ];
    
    public $timestamps = false;

    public function user() {
        return $this->belongsTo(User::class);
    }

    public function restaurant() {
        return $this->belongsTo(Restaurant::class);
    }  
}