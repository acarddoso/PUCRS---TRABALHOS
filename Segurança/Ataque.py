import re
import os

# Frequência das letras
FREQ_PT = {
    'a': 0.1463, 'b': 0.0104, 'c': 0.0388, 'd': 0.0499, 'e': 0.1257,
    'f': 0.0102, 'g': 0.0130, 'h': 0.0128, 'i': 0.0618, 'j': 0.0040,
    'k': 0.0002, 'l': 0.0278, 'm': 0.0474, 'n': 0.0505, 'o': 0.1073,
    'p': 0.0252, 'q': 0.0120, 'r': 0.0653, 's': 0.0781, 't': 0.0434,
    'u': 0.0463, 'v': 0.0167, 'w': 0.0001, 'x': 0.0021, 'y': 0.0001,
    'z': 0.0047
}

def calcular_ic(texto):
    """
    Calcula o Índice de Coincidência de um texto.
    """
    n = len(texto)
    if n <= 1:
        return 0
    
    # Quantas vezes cada letra aparece 
    frequencias = []
    for letra in "abcdefghijklmnopqrstuvwxyz":
        contagem = texto.count(letra)
        frequencias.append(contagem)

    # Conta quantos pares de letras iguais existem
    pares_iguais = 0
    for f in frequencias:
        pares_iguais += f * (f - 1)

    # Total de pares possíveis
    pares_totais = n * (n - 1)

    # IC = chance de pegar 2 letras iguais
    ic = pares_iguais / pares_totais
    return ic

def estimar_tamanho_chave(texto, max_len=15):
    """
    Estima o tamanho da chave calculando o IC médio para diferentes tamanhos.
    """
    melhor_tamanho = 1
    maior_ic_medio = 0
    
    print("\n--- Análise de Índice de Coincidência ---")
    for tamanho in range(1, max_len + 1):
        ics = []
        # Divide o texto em subtextos/colunas
        for i in range(tamanho):
            subtexto = texto[i::tamanho]
            ics.append(calcular_ic(subtexto))
        
        # Calcula o IC medio de todos os subtextos/colunas
        ic_medio = sum(ics) / len(ics)
        print(f"Tamanho da chave {tamanho:2d} -> IC Médio: {ic_medio:.4f}")
        
        # Desempate por maior IC medio
        if ic_medio > maior_ic_medio + 0.005:
            maior_ic_medio = ic_medio
            melhor_tamanho = tamanho
            
    return melhor_tamanho

def descobrir_senha(texto, tamanho_chave):
    """
    Descobre a senha realizando análise de frequência em cada posição da chave.
    """
    senha = ""
    print("\n--- Análise de Frequência ---")
    for i in range(tamanho_chave):
        subtexto = texto[i::tamanho_chave]
        melhor_deslocamento = 0
        maior_pontuacao = 0
        
        for deslocamento in range(26):
            pontuacao = 0
           
            # Conta as letras no subtexto decifrado com o deslocamento atual
            for char in subtexto:
                # Decifra o caractere
                numero = ord(char) - ord('a')                    # letra vira numero
                numero_decifrado = (numero - deslocamento) % 26  # subtrai e da a volta
                char_decifrado = chr(numero_decifrado + ord('a'))  # numero vira letra
                pontuacao += FREQ_PT[char_decifrado]
                
            if pontuacao > maior_pontuacao:
                maior_pontuacao = pontuacao
                melhor_deslocamento = deslocamento
                
        letra_senha = chr(melhor_deslocamento + ord('a'))
        senha += letra_senha
        print(f"Posição {i+1}: Letra mais provável '{letra_senha}' (Deslocamento {melhor_deslocamento})")
        
    return senha

def decifrar_vigenere(texto, senha):
    """
    Decifra o texto usando a cifra de Vigenère com a senha conhecida.
    """
    texto_decifrado = []
    
    for i, char in enumerate(texto):
        deslocamento = ord(senha[i % len(senha)]) - ord('a')
        numero = ord(char) - ord('a')                    # letra vira numero
        numero_decifrado = (numero - deslocamento) % 26  # subtrai e da a volta
        char_decifrado = chr(numero_decifrado + ord('a'))  # numero vira letra
        texto_decifrado.append(char_decifrado)
        
    return ''.join(texto_decifrado)

def main():
    print("=== CRIPTOANÁLISE: QUEBRA DA CIFRA DE VIGENÈRE ===")
    arquivo_entrada = input("Digite o nome do arquivo cifrado (ex: texto_criptografado.txt): ")
    
    if not os.path.exists(arquivo_entrada):
        print(f"Erro: Arquivo '{arquivo_entrada}' não encontrado.")
        return

    with open(arquivo_entrada, 'r', encoding='utf-8', errors='ignore') as f:
        texto_cifrado = f.read().strip()

    if not texto_cifrado.isalpha() or not texto_cifrado.islower():
         print("Aviso: O texto cifrado deve conter apenas letras minúsculas sem espaços. Limpando texto...")
         texto_cifrado = re.sub(r'[^a-z]', '', texto_cifrado.lower())

    # 1 - Descobrir o tamanho da chave
    tamanho_estimado = estimar_tamanho_chave(texto_cifrado, max_len=20)
    print(f"\n=> Tamanho estimado da senha: {tamanho_estimado}")
    
    # 2 - Descobrir a senha usando análise de frequencia
    senha_descoberta = descobrir_senha(texto_cifrado, tamanho_estimado)
    print(f"\n=> Senha descoberta: {senha_descoberta}")
    
    # 3 - Decifrar o texto
    texto_decifrado = decifrar_vigenere(texto_cifrado, senha_descoberta)
    
    arquivo_saida = "texto_decifrado.txt"
    with open(arquivo_saida, 'w', encoding='utf-8') as f:
        f.write(texto_decifrado)
        
    print(f"\nTexto decifrado com sucesso! Salvo em '{arquivo_saida}'.")
    
    amostra = texto_decifrado[:200]
    print(f"\nAmostra do texto decifrado:\n{amostra}")

if __name__ == "__main__":
    main()
