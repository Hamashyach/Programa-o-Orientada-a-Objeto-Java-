package gestaopizzaria.DAO;

import gestaopizzaria.DiadeTrabalho;
import java.util.ArrayList;
import java.util.List;

public class DiadeTrabalhoDAO {

    private final List<DiadeTrabalho> diasDeTrabalho = new ArrayList<>();

    public void cadastrarDiaDeTrabalho(DiadeTrabalho diaDeTrabalho) {
        diasDeTrabalho.add(diaDeTrabalho);
    }

    public List<DiadeTrabalho> listarDiasDeTrabalho() {
        return diasDeTrabalho;
    }

    public DiadeTrabalho buscarDiaDeTrabalhoPorId(int id) {
        if (id > 0 && id <= diasDeTrabalho.size()) {
            return diasDeTrabalho.get(id - 1);
        }
        return null;
    }

    public boolean excluirDiaDeTrabalho(int id) {
        DiadeTrabalho diaDeTrabalho = buscarDiaDeTrabalhoPorId(id);
        if (diaDeTrabalho != null) {
            diasDeTrabalho.remove(diaDeTrabalho);
            return true;
        }
        return false;
    }
}
