
import re
import os

def higienizar_texto(texto):
    """
    Higieniza o texto: converte tudo para minusculo e remove acentos.
    """
    # Tudo minúsculo
    texto = texto.lower()

    # Remove acentos usando tabela de traduçao nativa
    com_acentos = "áàãâäéèêëíìîïóòõôöúùûüç"
    sem_acentos = "aaaaaeeeeiiiiooooouuuuc"
    tabela = str.maketrans(com_acentos, sem_acentos)
    texto = texto.translate(tabela)
   
    # Fica so com letras a-z
    texto = re.sub(r'[^a-z]', '', texto)
    return texto

def cifrar_vigenere(texto, senha):
    """
    Aplica a cifra de Vigenère ao texto usando a senha fornecida.
    """
    texto_cifrado = []
    senha_higienizada = higienizar_texto(senha)
    
    if not senha_higienizada:
         raise ValueError("A senha deve ter pelo menos uma letra.")

    # Criptografa letra por letra
    for i, char in enumerate(texto):
        # 1. Descobre qual letra da senha usar nesta posição
        letra_senha = senha_higienizada[i % len(senha_higienizada)]

        # 2. Calcula o deslocamento
        deslocamento = ord(letra_senha) - ord('a')
        
        # 3. Transforma a letra original do texto em um numero de 0 a 25
        numero_letra = ord(char) - ord('a')
        
        # 4. Aplica o deslocamento e usa % 26 para dar a volta se passar de Z
        novo_numero = (numero_letra + deslocamento) % 26
        
        # 5. Transforma o numero resultante de volta em letra
        char_cifrado = chr(novo_numero + ord('a'))
        
        # 6. Adiciona a letra cifrada na lista
        texto_cifrado.append(char_cifrado)
        
    return ''.join(texto_cifrado)

def main():
    print("=== CRIPTOGRAFIA: CIFRA DE VIGENÈRE ===")
    arquivo_entrada = input("Digite o nome do arquivo de entrada (ex: texto.txt): ")
    
    if not os.path.exists(arquivo_entrada):
        print(f"Erro: Arquivo '{arquivo_entrada}' não encontrado.")
        return

    with open(arquivo_entrada, 'r', encoding='utf-8', errors='ignore') as f:
        texto_original = f.read()

    senha = input("Digite a senha (chave): ")
    
    texto_higienizado = higienizar_texto(texto_original)
    
    if not texto_higienizado:
        print("Erro: O texto não contém letras para serem cifradas.")
        return

    try:
        texto_cifrado = cifrar_vigenere(texto_higienizado, senha)
    except ValueError as e:
        print(f"Erro: {e}")
        return

    arquivo_saida = "texto_criptografado.txt"
    with open(arquivo_saida, 'w', encoding='utf-8') as f:
        f.write(texto_cifrado)
        
    print(f"Texto cifrado com sucesso! Salvo em '{arquivo_saida}'.")

if __name__ == "__main__":
    main()
