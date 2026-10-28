package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de Validação da Classe Usuario")
class UsuarioTest {

    @Nested
    @DisplayName("1. Validação de Campos Nulos e Vazios")
    class ValidacaoCamposNulosEVazios {

        @Test
        @DisplayName("Deve retornar falso quando a String for nula")
        void deveRetornarFalsoParaStringNula() {
            assertFalse(Usuario.validarCampoNaoNuloOuVazio(null));
        }

        @Test
        @DisplayName("Deve retornar falso quando a String for vazia ou contiver apenas espaços")
        void deveRetornarFalsoParaStringVazia() {
            assertFalse(Usuario.validarCampoNaoNuloOuVazio(""));
            assertFalse(Usuario.validarCampoNaoNuloOuVazio("   "));
        }

        @Test
        @DisplayName("Deve retornar verdadeiro para String preenchida")
        void deveRetornarVerdadeiroParaStringValida() {
            assertTrue(Usuario.validarCampoNaoNuloOuVazio("Texto valido"));
        }
    }

    @Nested
    @DisplayName("2. Validação do Nome")
    class ValidacaoNome {

        @ParameterizedTest
        @ValueSource(strings = {"João Silva", "Maria", "Ana Clara de Souza"})
        @DisplayName("Deve aceitar nomes válidos")
        void deveAceitarNomesValidos(String nomeValido) {
            assertTrue(Usuario.validarNome(nomeValido));
        }

        @ParameterizedTest
        @ValueSource(strings = {"João123", "Maria_Silva", "Ana @ Maria", " Pedro", "Pedro "})
        @DisplayName("Deve rejeitar nomes inválidos")
        void deveRejeitarNomesInvalidos(String nomeInvalido) {
            assertFalse(Usuario.validarNome(nomeInvalido));
        }
    }

    @Nested
    @DisplayName("3, 4 e 5. Validação do E-mail")
    class ValidacaoEmail {

        @ParameterizedTest
        @ValueSource(strings = {
                "usuario@dominio",
                "joao.silva@empresa.com",
                "maria_123@servidor.org"
        })
        @DisplayName("Deve aceitar e-mails válidos")
        void deveAceitarEmailsValidos(String emailValido) {
            assertTrue(Usuario.validarEmail(emailValido));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "usuário@dominio.com",
                "usuario#123@dominio.com",
                "usuario@dominio_com",
                "usuario.dominio.com"
        })
        @DisplayName("Deve rejeitar e-mails inválidos")
        void deveRejeitarEmailsInvalidos(String emailInvalido) {
            assertFalse(Usuario.validarEmail(emailInvalido));
        }
    }
}