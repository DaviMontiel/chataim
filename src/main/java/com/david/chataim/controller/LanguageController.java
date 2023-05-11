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
		"El Nombre solo puede contener letras y numeros, hasta 25 caracteres",
		"El Email esta mal construido",
		"La Contraseña solo puede contener letras, numeros y @!-_ de 8 a 100 caracteres",
		"El correo introducido ya ha sido registrado",
		
		// MAIL
		"CODIGO DE VERIFICACIÓN",
		"Parece que el servidor esta siendo lento. Espere por favor",
		"No se a podido enviar el correo electronico, reviselo e intentelo mas tarde",
		"Mensaje de verificacion enviado a su correo electronico",

		// VERIFICATION PANEL
		"Codigo de Verificación",
		"Revisa tu correo para obtener el código de verificación",
		"CONFIRMAR",
		"CANCELAR",
		
		// INIT CONFIGURATION ACCOUNT
		"Configuracion de la Cuenta",
		"SELECCIONAR IMAGEN",
		"Descripcion de la Cuenta:",
		"caracteres",
		"Modo Anonimo",
		"Al marcar la casilla, podrás hablar con un usuario aleatorio que haya aceptado las mismas condiciones. Se agregará un contacto adicional a tu lista, seleccionado al azar entre aquellos que hayan aceptado los términos y condiciones correspondientes.",
		"Terminos y Condiciones",
		"CONTINUAR",
		"La descripcion supera el limite, o contiene carateres no validos",
		"Acepta los términos y condiciones para continuar",
		
		// OTHER
		"No se a encontrado ninguna cuenta",
		"Añadir Contacto",
		"ID Code",
		"AGREGAR",
		"Rellene el ID del usuario",
		"Usuario no encontrado",
		"No se a podido crear el usuario, intentelo mas tarde"
		
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
		"The Name can only contain letters and numbers, up to 25 characters.",
		"Email is poorly constructed",
		"Password can only contain letters, numbers and @!-_ from 8 to 100 characters.",
		"The email entered has already been registered",
		
		// MAIL
		"VERIFICATION CODE",
		"It seems that the server is slow. Please wait",
		"The email could not be sent, please check and try again later",
		"Verification message sent to your email",
		
		// VERIFICATION PANEL
		"Verification Code",
		"Check your mail to get verify code",
		"OK",
		"CANCEL",
		
		// INIT CONFIGURATION ACCOUNT
		"Account Configuration",
		"CHOOSE FILE",
		"Contact Description:",
		"characters",
		"Anonymous Mode",
		"By checking the box, you will be able to talk to a random user who has agreed to the same conditions. An additional contact will be added to your list, randomly selected from those who have agreed to the applicable terms and conditions.",
		"Terms and Conditions",
		"CONTINUE",
		"The description exceeds the character limit, or contains invalid characters",
		"Accept the terms and conditions to continue",
		
		// OTHER
		"No account found",
		"Add Contact",
		"ID Code",
		"ADD",
		"Fill User ID",
		"User not found",
		"Failed to create user, try again later"
		
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