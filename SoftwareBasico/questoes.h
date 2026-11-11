#ifndef QUESTOES_H
#define QUESTOES_H

struct Questao
{
    int identificador;
    char enunciado[300];
    char alternativaA[300];
    char alternativaB[300];
    char alternativaC[300];
    char alternativaD[300];
    int gabarito;
    char mensagemAcerto[300];
    char mensagemErro[300];
};

void mostrarQuestao(struct Questao quest);
int lerResposta(void);
int carregarModulo(char nomeArq[], struct Questao quest[]);
void embaralharQuestoes(struct Questao questoes[], int quantidade);
struct Questao embaralharAlternativas(struct Questao questao);

#endif