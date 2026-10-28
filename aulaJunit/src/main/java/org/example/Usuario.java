package org.example;

/**
 * Classe de modelo Usuario contendo as regras de validação.
 */
public class Usuario {

    private String nome;
    private String email;

    public Usuario() {
    }

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // --- Getters e Setters ---

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // --- Métodos de Validação ---

    /**
     * Regra 1: Campos do tipo String não podem ser nulos ou vazios.
     */
    public static boolean validarCampoNaoNuloOuVazio(String campo) {
        return campo != null && !campo.trim().isEmpty();
    }

    /**
     * Regra 2: Nome não pode conter caracteres especiais ou números,
     * sendo permitido apenas espaço entre as palavras.
     */
    public static boolean validarNome(String nome) {
        if (!validarCampoNaoNuloOuVazio(nome)) {
            return false;
        }
        // Aceita apenas letras (com ou sem acentuação) e espaços simples entre palavras
        return nome.matches("^[a-zA-ZÀ-ÿ]+(\\s[a-zA-ZÀ-ÿ]+)*$");
    }

    /**
     * Regras 3, 4 e 5:
     * 3. E-mail deve estar no padrão usuario@dominio
     * 4. 'usuario' pode conter apenas caracteres alfanuméricos não acentuados, ponto (.) ou sublinhado (_)
     * 5. 'dominio' pode conter apenas caracteres alfanuméricos e ponto (.)
     */
    public static boolean validarEmail(String email) {
        if (!validarCampoNaoNuloOuVazio(email)) {
            return false;
        }

        // Divide o email pelo símbolo '@'
        String[] partes = email.split("@");
        if (partes.length != 2) {
            return false;
        }

        String usuario = partes[0];
        String dominio = partes[1];

        // Validação da parte do usuário (não acentuado, ., _)
        boolean usuarioValido = usuario.matches("^[a-zA-Z0-9._]+$");

        // Validação da parte do domínio (alfanumérico e ponto para extensão)
        boolean dominioValido = dominio.matches("^[a-zA-Z0-9.]+$");

        return usuarioValido && dominioValido;
    }

    /**
     * Método utilitário para validar o estado da instância completa do Usuário.
     */
    public boolean ehValido() {
        return validarNome(this.nome) && validarEmail(this.email);
    }
}