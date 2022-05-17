<?php

namespace App\Traits;

use Carbon\Carbon;

/*
|--------------------------------------------------------------------------
| Api Responser Trait
|--------------------------------------------------------------------------
|
| This trait will be used for any response we sent to clients.
|
*/

trait ApiResponser
{
	/**
     * Return a success JSON response.
     *
     * @param  array|string  $data
     * @param  string  $message
     * @param  int|NULL  $code
     * @return \Illuminate\Http\JsonResponse
     */
	protected function success($data, int $code, string $message = NULL)
	{
		return response()->json([
			'status' => 'Success',
			'message' => $message,
			'data' => $data
		], $code);
	}

	/**
     * Return a named JSON response.
     *
     * @param  string  $name
     * @param  array|string  $data
     * @param  int| $code
     * @return \Illuminate\Http\JsonResponse
     */
	protected function return_JSON($name, $data, int $code)
	{
		return response()->json([
			$name => $data
		], $code);
	}

	/**
     * Return an error JSON response.
     *
     * @param  array  $errors
     * @param  int  $code
     * @param  array|string|NULL  $data
     * @return \Illuminate\Http\JsonResponse
     */
	protected function error(int $code, $errors = NULL, $data = NULL)
	{
		return response()->json([
			'status' => 'Error',
			'errors' => $errors,
			'data' => $data
		], $code);
	}

	/**
     * Return an array containing the failed inputs and the error messages to display.
     *
     * @param  array  $errors
     * @param  int  $code
     * @param  array|string|NULL  $data
     * @return \Illuminate\Http\JsonResponse
     */
	protected function error_messages($errors)
	{
		$message = '';
		$result = [];
		$keys = array_keys($errors);
		foreach ($keys as $key) {
			$result[$key] = null;
		}
		for($len = count($keys), $i = 0; $i < $len; $i++) {
			switch (key($errors[$keys[$i]])) {
				case 'Required':
					$message = 'Champ obligatoire.';
					break;
				case 'Min':
					$message = ''.$errors[$keys[$i]]['Min'][0].' charactères minimum.';
					break;
				case 'Max':
					$message = ''.$errors[$keys[$i]]['Max'][0].' charactères maximum.';
					break;
				case 'Unique':
					$message = 'Ce pseudo est déjà pris.';
					break;
				case 'Integer':
					$message = 'Le champ ne peut contenir que des chiffres.';
					break;
				case 'Confirmed':
					$message = 'Les mots de passe doivent être identiques.';
					break;
				case 'Email':
					$message = 'L\'email est invalide.';
					break;
				case 'Numeric':
					$message = 'Le champ ne peut contenir que des chiffres';
					break;
			}
			$result[$keys[$i]] = $message;
		}
		return $result;
	}
}
