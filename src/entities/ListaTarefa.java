package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ListaTarefa {
    private final ArrayList<Tarefa> listaTarefas;

    public ListaTarefa() {
        this.listaTarefas = new ArrayList<>();
    }

    public void adicionaTarefa(Tarefa tarefa) {
        listaTarefas.add(tarefa);
    }

    public void editarTarefa(int indice, Tarefa novaTarefa) {
        listaTarefas.set(indice, novaTarefa);
    }

    public boolean excluirTarefa(int indice) {
        listaTarefas.remove(indice);
        return false;
    }

    public void listarTarefas() {
        for (int i = 0; i < listaTarefas.size(); i++) {
            Tarefa tarefa = listaTarefas.get(i);
            System.out.println(i + ". " + tarefa.getNome() + " - " + tarefa.getDescricao() + " (" +
                    tarefa.getCategoria() + ") - Criada em: " +
                    tarefa.getDataCriacao().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                    " - Concluída em: " + (tarefa.isConcluida() ?
                    tarefa.getDataConclusao().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "Não concluída"));
        }
    }

    public void filtrarPorCategoria(String categoria) {
        for (int i = 0; i < listaTarefas.size(); i++) {
            Tarefa tarefa = listaTarefas.get(i);
            if (tarefa.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(i + ". " + tarefa.getNome() + " - " + tarefa.getDescricao() + " (" +
                        tarefa.getCategoria() + ") - Criada em: " +
                        tarefa.getDataCriacao().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                        " - Concluída em: " + (tarefa.isConcluida() ?
                        tarefa.getDataConclusao().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "Não concluída"));
            }
        }
    }

    public void filtrarPorData(LocalDateTime data) {
        for (int i = 0; i < listaTarefas.size(); i++) {
            Tarefa tarefa = listaTarefas.get(i);
            if (tarefa.getDataCriacao().equals(data)) {
                System.out.println(i + ". " + tarefa.getNome() + " - " + tarefa.getDescricao() + " (" +
                        tarefa.getCategoria() + ") - Criada em: " +
                        tarefa.getDataCriacao().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                        " - Concluída em: " + (tarefa.isConcluida() ?
                        tarefa.getDataConclusao().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "Não concluída"));
            }
        }
    }

    public Tarefa buscarTarefa(int numeroTarefa) {
        for (Tarefa tarefa : listaTarefas) {
            if (tarefa.getNumero() == numeroTarefa) {
                return tarefa;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    public int size(int size) {
        return size;
    }
}

