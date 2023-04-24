package com.david.chataim.controller;

import lombok.Getter;

public class LanguageController {

	public static enum LANGUAGE {ES, EN}
	@Getter public static LANGUAGE language;
	
	
	private static final String[] ES = {
		"VACIO",
		"Busca un chat o inicia uno nuevo.",
		
		// REGISTER
		"¡Hola, amigo!",
		"Introduzca tus datos personales",
		"y empieza la aventura con nosotros",
		"REGISTRARSE",
		
		"¡Bienvenido de nuevo!",
		"Recuerda mantente conectado con nosotros",
		"entra con tu informacion personal",
		"INICIAR SESIÓN",
		
		// LOGIN
		"Inicar Sesión",
		"Email",
		"Contraseña",
		"¿Se te olvidó la contraseña?",
		
		"Crear Cuenta",
		"Nombre",
		"Email",
		"Constraseña",
		
		// PATTERN
		"Complete todos los campos",
		"El Nombre solo puede contener letras y numeros",
		"El Email esta mal construido",
		"La Contraseña solo puede contener letras, numeros y @!-_",
		
		// MAIL
		"CODIGO DE VERIFICACIÓN",
		"Parece que el servidor va lento. Espere por favor",
		"No se a podido enviar el correo electronico, reviselo e intentelo mas tarde",
		"Mensaje de verificacion enviado a su correo electronico"
	};
	
	private static final String[] EN = {
		"EMPTY",
		"Search for a chat or start a new one",
		
		// REGISTER
		"hello, Friend!",
		"Enter your personal details",
		"and start journey with us",
		"SIGN UP",
		
		"Welcome Back!",
		"To keep connected with us please",
		"login with your personal info",
		"SIGN IN",
		
		// LOGIN
		"Sign In",
		"Email",
		"Password",
		"Forgot your password?",
		
		"Create Account",
		"Name",
		"Email",
		"Password",
		
		// PATTERN
		"Complete all fields",
		"The Name can only contain letters and numbers",
		"Email is poorly constructed",
		"Password can only contain letters, numbers and @!-_",
		
		// MAIL
		"VERIFICATION CODE",
		"It seems that the server is slow. Please wait",
		"The email could not be sent, please check and try again later",
		"Verification message sent to your email"
	};
	
	public static void setLanguage(LANGUAGE lang) {
		language = lang;
	}//SET
	
	public static String getWord(int index) {
		switch (language) {
			case ES:
				return ES[index];
			case EN:
				return EN[index];
		}//SWITCH
		
		return null;
	}//GET
}//CLASS