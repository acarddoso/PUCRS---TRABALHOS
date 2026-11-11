#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "questoes.h"

void mostrarQuestao(struct Questao quest) {
    printf("%s\n", quest.enunciado);
    printf("A) %s\n", quest.alternativaA);
    printf("B) %s\n", quest.alternativaB);
    printf("C) %s\n", quest.alternativaC);
    printf("D) %s\n", quest.alternativaD);
}

int lerResposta(void) {
    char resposta;

    while (1){
        printf("Digite sua resposta: ");
        scanf(" %c", &resposta);

        if (resposta == 'A' || resposta == 'a') {
            return 1;
        } else if (resposta == 'B' || resposta == 'b') {
            return 2;
        } else if (resposta == 'C' || resposta == 'c') {
            return 3;
        } else if (resposta == 'D' || resposta == 'd') {
            return 4;
        } else {
            printf("Opção invalida! Digite A, B, C ou D.\n");
        }
    }

}

void lerTexto(FILE *arquivo, char texto[300]) {
    if (fgets(texto, 300, arquivo) == NULL) {
        printf("Erro: nao foi possivel ler um texto da questao.\n");
        fclose(arquivo);
        exit(1);
    }

    // Retira a quebra de linha que o fgets pode guardar no texto.
    texto[strcspn(texto, "\r\n")] = '\0';
}

struct Questao lerQuestao(FILE *arquivo) {
    struct Questao questao;

    if (fscanf(arquivo, "%d", &questao.identificador) != 1) {
        printf("Erro: identificador da questao invalido.\n");
        fclose(arquivo);
        exit(1);
    }

    // Consome a quebra de linha depois do numero antes de ler os textos.
    fgetc(arquivo);

    lerTexto(arquivo, questao.enunciado);
    lerTexto(arquivo, questao.alternativaA);
    lerTexto(arquivo, questao.alternativaB);
    lerTexto(arquivo, questao.alternativaC);
    lerTexto(arquivo, questao.alternativaD);

    if (fscanf(arquivo, "%d", &questao.gabarito) != 1) {
        printf("Erro: gabarito da questao invalido.\n");
        fclose(arquivo);
        exit(1);
    }

    if (questao.gabarito < 1 || questao.gabarito > 4) {
        printf("Erro: o gabarito deve estar entre 1 e 4.\n");
        fclose(arquivo);
        exit(1);
    }

    fgetc(arquivo);

    lerTexto(arquivo, questao.mensagemAcerto);
    lerTexto(arquivo, questao.mensagemErro);

    return questao;
}

int carregarModulo(char nomeArq[], struct Questao quest[]) {
    FILE *arquivo;
    int quantidade = 0;
    int caractere;

    arquivo = fopen(nomeArq, "r");

    if (arquivo == NULL) {
        printf("Não foi possivel abrir o arquivo: %s\n", nomeArq);
        return 0;
    }

    while (quantidade < 100) {
        caractere = fgetc(arquivo);

        if (caractere == EOF) {
            break;
        }

        // Devolve o caractere usado para verificar o fim do arquivo.
        ungetc(caractere, arquivo);

        quest[quantidade] = lerQuestao(arquivo);
        quantidade++;
    }

    fclose(arquivo);
    return quantidade;
}

void embaralharQuestoes(struct Questao questoes[], int quantidade) {
    int i;
    int sorteada;
    struct Questao temp;

    // Troca cada questao com uma posicao sorteada entre 0 e i.
    for (i = quantidade - 1; i > 0; i--) {
        sorteada = rand() % (i + 1);

        temp = questoes[i];
        questoes[i] = questoes[sorteada];
        questoes[sorteada] = temp;
    }
}

struct Questao embaralharAlternativas(struct Questao questao) {
    char alternativas[4][300];
    char temporaria[300];
    int i;
    int sorteada;

    // A matriz temporaria permite trocar as alternativas usando indices.
    strcpy(alternativas[0], questao.alternativaA);
    strcpy(alternativas[1], questao.alternativaB);
    strcpy(alternativas[2], questao.alternativaC);
    strcpy(alternativas[3], questao.alternativaD);

    for (i = 3; i > 0; i--)
    {
        sorteada = rand() % (i + 1);

        strcpy(temporaria, alternativas[i]);
        strcpy(alternativas[i], alternativas[sorteada]);
        strcpy(alternativas[sorteada], temporaria);

        // O gabarito acompanha a troca; ele usa 1 a 4 e os indices usam 0 a 3.
        if (questao.gabarito == i + 1)
        {
            questao.gabarito = sorteada + 1;
        }
        else if (questao.gabarito == sorteada + 1)
        {
            questao.gabarito = i + 1;
        }
    }

    strcpy(questao.alternativaA, alternativas[0]);
    strcpy(questao.alternativaB, alternativas[1]);
    strcpy(questao.alternativaC, alternativas[2]);
    strcpy(questao.alternativaD, alternativas[3]);

    return questao;
}
