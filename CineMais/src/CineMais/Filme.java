package CineMais;

    public class Filme {
        private String nome;
        private String diretor;
        private String genero;
        private String ano;
        private String sinopse;
    
        public Filme(String nome, String diretor, String genero, String ano, String sinopse) {
            this.nome = nome;
            this.diretor = diretor;
            this.genero = genero;
            this.ano = ano;
            this.sinopse = sinopse;
        }
    
        public String getNome() {
            return nome;
        }
    
        public String getDiretor() {
            return diretor;
        }
    
        public String getGenero() {
            return genero;
        }
    
        public String getAno() {
            return ano;
        }
    
        public String getSinopse() {
            return sinopse;
        }
    }
    

