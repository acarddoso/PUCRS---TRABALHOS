#include <stdio.h>
#include <string.h>
#include <time.h>
#include "usuarios.h"

void salvarResultado(char nomeUser[], double pontos) {
    char nomeArq[60];
    FILE *arquivo;

    time_t agr;
    struct tm *dataAtual;
    char data[11];

    sprintf(nomeArq, "%s.txt", nomeUser);
    // O modo a acrescenta a sessao sem apagar os resultados anteriores.
    arquivo = fopen(nomeArq, "a");

    if (arquivo == NULL) {
        printf("Nao foi possivel salvar o resultado\n");
        return;
    }

    agr = time(NULL);
    dataAtual = localtime(&agr);

    strftime(data, sizeof(data), "%Y-%m-%d", dataAtual);
    fprintf(arquivo, "%s %.2f\n", data, pontos);

    fclose(arquivo);
    printf("Resultado salvo em %s.\n", nomeArq);
}

void calcularOntem(char data[], char ontem[]) {
    int ano;
    int mes;
    int dia;
    struct tm calendario = {0};

    sscanf(data, "%d-%d-%d", &ano, &mes, &dia);
    // A struct tm conta anos desde 1900 e meses de 0 a 11.
    calendario.tm_year = ano - 1900;
    calendario.tm_mon = mes - 1;
    calendario.tm_mday = dia - 1;  
    calendario.tm_hour = 12;
    calendario.tm_isdst = -1;

    // Ajusta o dia anterior inclusive nas mudancas de mes, ano e anos bissextos.
    mktime(&calendario);
    strftime(ontem, 11, "%Y-%m-%d", &calendario);
}

int calcularSequencia(FILE *arquivo) {
    char data[11];
    char procurada[11] = "";
    char ontem[11];
    double pontos;
    int encontrou;
    int sequencia = 0;

    rewind(arquivo);

    // No formato AAAA-MM-DD, a comparacao dos textos encontra a data mais recente.
    while (fscanf(arquivo, "%10s %lf", data, &pontos) == 2) {
        if (strcmp(data, procurada) > 0) {
            strcpy(procurada, data);
        }
    }

    if (procurada[0] == '\0') {
        return 0;
    }

    // Procura cada dia anterior ate encontrar uma data sem utilizacao.
    while (1) {
        encontrou = 0;
        rewind(arquivo);

        while (fscanf(arquivo, "%10s %lf", data, &pontos) == 2) {
            if (strcmp(data, procurada) == 0) {
                // Encerra a busca para contar o dia apenas uma vez, mesmo com varias sessoes.
                encontrou = 1;
                break;
            }
        }
        if (encontrou == 0) {
            break;
        }

        sequencia++;
        calcularOntem(procurada, ontem);
        strcpy(procurada, ontem);
    }
    return sequencia;
}

void mostrarEstatisticas(char nomeUser[]) {
    char nomeArq[60];
    char data[11];
    int sequencia;
    FILE *arquivo;

    int utilizacoes = 0;
    double pontos;
    double total = 0;
    double media = 0;

    sprintf(nomeArq, "%s.txt", nomeUser);
    arquivo = fopen(nomeArq, "r");

    if (arquivo == NULL) {
        printf("Usuario ainda nao possui historico.\n");
        return;
    }

    while (fscanf(arquivo, "%10s %lf", data, &pontos) == 2) {
        utilizacoes ++;
        total = total + pontos;
    }

    sequencia = calcularSequencia(arquivo);
    fclose(arquivo);

    if (utilizacoes == 0) {
        printf("O historico esta vazio.\n");
        return;
    }

    media = total / utilizacoes;

    printf("\nEstatisticas de %s:\n", nomeUser);
    printf("Utilizacoes: %d\n", utilizacoes);
    printf("Pontuacao acumulada: %.2f\n", total);
    printf("Media: %.2f\n", media);
    printf("Dias consecutivos recentes: %d\n", sequencia);
}
