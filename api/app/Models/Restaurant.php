<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use App\Models\User;
use App\Models\Menu;
use App\Models\Grade;

class Restaurant extends Model
{
    use HasFactory;

    protected $fillable = [
        'name',
        'description',
        'grade',
        'localization',
        'phone_number',
        'website',
        'hours',
        'user_id'
    ];
    
    protected $hidden = [
        'user_id',
    ];

    public function user() {
        return $this->belongsTo(User::class);
    }
    
    public function menus() {
        return $this->hasMany(Menu::class);
    }

    public function grades() {
        return $this->hasMany(Grade::class);
    }

    public $timestamps = false;
    
}
