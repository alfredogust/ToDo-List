package application;

import entities.ListaTarefa;
import entities.Tarefa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Programa {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);

        ListaTarefa listaTarefa = new ListaTarefa();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    adicionarTarefa(sc, listaTarefa);
                    break;
                case 2:
                    editarTarefa(sc, listaTarefa);
                    break;
                case 3:
                    excluirTarefa(sc, listaTarefa);
                    break;
                case 4:
                    marcarTarefaConcluida(sc, listaTarefa);
                    break;
                case 5:
                    filtrarTarefas(sc, listaTarefa);
                    break;
                case 6:
                    listaTarefa.listarTarefas();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

    }
    private static void exibirMenu() {
        System.out.println("---- MENU ----");
        System.out.println("1. Adicionar tarefa");
        System.out.println("2. Editar tarefa");
        System.out.println("3. Excluir tarefa");
        System.out.println("4. Marcar tarefa como concluída ou não concluída");
        System.out.println("5. Filtrar tarefas");
        System.out.println("6. Listar todas as tarefas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    //bug
    private static void adicionarTarefa(Scanner sc, ListaTarefa listaTarefa) {
        System.out.println("---- ADICIONAR TAREFA ----");
        System.out.print("Nome da tarefa: ");
        String nome = sc.nextLine();
        System.out.print("Descrição da tarefa: ");
        String descricao = sc.nextLine();
        System.out.print("Categoria da tarefa: ");
        String categoria = sc.nextLine();
        System.out.print("Data e hora de criação da tarefa (no formato yyyy-MM-dd HH:mm:ss): ");
        LocalDateTime dataCriacao = LocalDateTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.print("Data e hora de conclusão da tarefa (no formato yyyy-MM-dd HH:mm:ss, deixe em branco se a tarefa não foi concluída): ");
        String dataConclusaoStr = sc.nextLine();
        LocalDateTime dataConclusao = null;
        if (!dataConclusaoStr.isEmpty()) {
            dataConclusao = LocalDateTime.parse(dataConclusaoStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        Tarefa tarefa = new Tarefa(nome, descricao, categoria, dataCriacao, dataConclusao);
        listaTarefa.adicionaTarefa(tarefa);

        System.out.println("Tarefa adicionada com sucesso.");
    }


    //bug
    private static void editarTarefa(Scanner sc, ListaTarefa listaTarefa) {
        if (listaTarefa.isEmpty()) {
            System.out.println("Não há tarefas para editar.");
            return;
        }

        System.out.println("Digite o número da tarefa que deseja editar:");
        int numeroTarefa = sc.nextInt();
        sc.nextLine();

        if (numeroTarefa <= 0 || numeroTarefa > listaTarefa.size(numeroTarefa)) {
            System.out.println("Número de tarefa inválido.");
            return;
        }

        Tarefa tarefa = listaTarefa.get(numeroTarefa - 1);

        System.out.println("Digite o novo nome da tarefa:");
        String nome = sc.nextLine();

        System.out.println("Digite a nova descrição da tarefa:");
        String descricao = sc.nextLine();

        System.out.println("Digite a nova categoria da tarefa:");
        String categoria = sc.nextLine();

        System.out.println("Digite a nova data de criação da tarefa (dd/MM/yyyy HH:mm):");
        LocalDateTime dataCriacao = LocalDateTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        System.out.println("Digite a nova data de conclusão da tarefa (dd/MM/yyyy HH:mm):");
        String dataConclusaoStr = sc.nextLine();
        LocalDateTime dataConclusao = null;
        if (!dataConclusaoStr.isEmpty()) {
            dataConclusao = LocalDateTime.parse(dataConclusaoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        }

        tarefa.setNome(nome);
        tarefa.setDescricao(descricao);
        tarefa.setCategoria(categoria);
        tarefa.setDataCriacao(dataCriacao);
        tarefa.setDataConclusao(dataConclusao);

        System.out.println("Tarefa editada com sucesso!");
    }


    private static void excluirTarefa(Scanner scanner, ListaTarefa listaTarefa) {
        System.out.println("---- EXCLUIR TAREFA ----");
        System.out.print("Digite o número da tarefa que deseja excluir: ");
        int numeroTarefa = scanner.nextInt();
        scanner.nextLine();
        boolean removida = listaTarefa.excluirTarefa(numeroTarefa);
        if (removida) {
            System.out.println("Tarefa removida com sucesso.");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private static void marcarTarefaConcluida(Scanner sc, ListaTarefa listaTarefa) {
        System.out.println("---- MARCAR TAREFA COMO CONCLUÍDA OU NÃO CONCLUÍDA ----");
        System.out.print("Digite o número da tarefa que deseja marcar: ");
        int numeroTarefa = sc.nextInt();
        sc.nextLine();
        Tarefa tarefa = listaTarefa.buscarTarefa(1);
        if (tarefa == null) {
            System.out.println("Tarefa não encontrada.");
        } else {
            System.out.print("Deseja marcar a tarefa como concluída (S/N)? ");
            String resposta = sc.nextLine().toUpperCase();
            boolean concluida;
            if (resposta.equals("S")) {
                tarefa.setConcluida(true);
                concluida = true;
            } else if (resposta.equals("N")) {
                tarefa.setConcluida(false);
                concluida = false;
            } else {
                System.out.println("Opção inválida.");
                return;
            }
            System.out.println("Tarefa " + (concluida ? "concluída" : "não concluída") + " com sucesso.");
        }
    }

    private static void filtrarTarefas(Scanner sc, ListaTarefa listaTarefa) {
        System.out.println("---- FILTRAR TAREFAS ----");
        System.out.println("1. Filtrar por categoria");
        System.out.println("2. Filtrar por data de criação");
        System.out.print("Escolha uma opção: ");
        int opcaoFiltro = sc.nextInt();
        sc.nextLine(); 

        switch (opcaoFiltro) {
            case 1:
                System.out.print("Digite a categoria para filtrar: ");
                String categoria = sc.nextLine();
                listaTarefa.filtrarPorCategoria(categoria);
                break;
            case 2:
                System.out.print("Digite a data para filtrar (no formato yyyy-MM-dd): ");
                String dataStr = sc.nextLine();
                listaTarefa.filtrarPorData(LocalDateTime.parse(dataStr));
                break;
            default:
                System.out.println("Opção inválida.");
        }
        sc.close();
    }
}
