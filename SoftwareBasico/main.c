#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include "questoes.h"
#include "usuarios.h"

#define TOTAL_MODULOS 3

int main(void) {
    char nomeUser[50];
    
    printf("Informe seu identificador (sem espacos): ");
    scanf("%49s", nomeUser);
    printf("Ola, %s!\n\n", nomeUser);

    struct Questao quest[100];
    struct Questao selecionadas[TOTAL_MODULOS];

    char nomesArquivos[TOTAL_MODULOS][100] = {
        "01-a_linguagem_de_programacao_c.txt",
        "02-funcoes_em_c_passagem_de_parametros.txt",
        "03-strings_structs_unions_enums.txt"
    };

    int quantidade;
    int sorteada;
    int i;

    srand(time(NULL));

    // Reutiliza o banco para cada modulo e guarda uma copia da questao sorteada.
    for (i = 0; i < TOTAL_MODULOS; i++) {
        quantidade = carregarModulo(nomesArquivos[i], quest);

        if (quantidade == 0) {
            printf("Nao foi possivel carregar o modulo %d.\n", i + 1);
            return 1;
        }

        printf("Modulo %d: %d questoes carregadas.\n", i + 1, quantidade);

        sorteada = rand() % quantidade;
        selecionadas[i] = quest[sorteada]; 
    }

    printf("\n");
    embaralharQuestoes(selecionadas, TOTAL_MODULOS);

    // Cada posicao vale 0 enquanto a questao esta pendente e 1 quando foi acertada.
    int acertadas[TOTAL_MODULOS] = {0};
    int totalAcertos = 0;
    int tentativas = 0;
    double pontos;
    int alternativaEscolhida;

    // Cada passagem pelo vetor e uma rodada; as questoes acertadas sao ignoradas.
    while (totalAcertos < TOTAL_MODULOS) {
        for (i = 0; i < TOTAL_MODULOS; i++) {
            if (acertadas[i] == 0) {
                // Embaralha novamente mesmo quando a questao esta sendo reaplicada.
                selecionadas[i] =  embaralharAlternativas(selecionadas[i]);
                mostrarQuestao(selecionadas[i]);
                alternativaEscolhida = lerResposta();
                tentativas++;

                if (alternativaEscolhida == selecionadas[i].gabarito) {
                    printf("Resposta correta!\n");
                    printf("%s\n", selecionadas[i].mensagemAcerto);
                    acertadas[i] = 1;
                    totalAcertos++;
                } else {
                    printf("Resposta incorreta!\n");
                    printf("%s\n", selecionadas[i].mensagemErro);
                }
                printf("\n");
            }
        }
        if (totalAcertos < TOTAL_MODULOS) {
            printf("Reaplicando questoes incorretas...\n\n");

        }
    }

    // As tentativas incluem as reaplicacoes; 10.0 evita a divisao inteira.
    pontos = 10.0 * totalAcertos / tentativas; 
    printf("Voce acertou em %d tentativa(s).\n", tentativas);
    printf("Pontuacao de %s: %.2f\n", nomeUser, pontos);

    salvarResultado(nomeUser, pontos);
    mostrarEstatisticas(nomeUser);

    return 0;
}
