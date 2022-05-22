package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code findCodeById(Long id) {
        return nullIfExpired(codeRepository.findCodeById(id));
    }

    public Code findByUuid(String uuid) {
        Code code = codeRepository.findByUuid(uuid);
        if (code == null) {
            return null;
        } else {
            return nullIfExpired(code);
        }

    }

    public List<Code> findAll() {
        List<Code> codeList = new ArrayList<>(codeRepository.findAll());
        List<Code> newList = new ArrayList<>();
        for (Code code : codeList) {
            if (nullIfExpired(code) != null) {
                newList.add(code);
            }
        }
        return newList;
    }

    public List<Code> safeFindAll() {
        return codeRepository.findAll();
    }

    public Code save(Code toSave) {
        return codeRepository.save(toSave);
    }

    public Code findCodeByEntryDateAsc(int n) {
        List<Code> codeList = new ArrayList<>(findAll());
        codeList.sort(Comparator.comparing(Code::getRawDate));
        return codeList.get(n - 1);
    }

    public List<Code> findAllByEntryDateDesc() {
        List<Code> codeList = new ArrayList<>(safeFindAll());
        codeList.sort(Comparator.comparing(Code::getRawDate).reversed());
        System.out.println("DEBUG - findAllByEntryDateDesc" + codeList.toString());
        return codeList;
    }

    public List<Code> findLatest(int amount) {
        List<Code> latestList = new ArrayList<>(findAllByEntryDateDesc());
        List<Code> newList = new ArrayList<>();
        for (Code c : latestList) {
            if (!c.isSecret()) {
                newList.add(c);
            }
        }
        if (newList.size() <= amount) {
            System.out.println("DEBUG - findLatest" + newList.toString());
            return newList.subList(0, latestList.size());
        } else {
            System.out.println("DEBUG - findLatest" + newList.toString());
            return newList.subList(0, amount);
        }
    }

    private Code nullIfExpired(Code code) {
        if (code.getExpiryTime() != null &&
                LocalDateTime.now().isAfter(code.getExpiryTime())) {
            System.out.println("DEBUG nullIfExpired Time Check: Deleting: " + code.toString());
            codeRepository.delete(code);
            return null;
        } else if (code.hasViewsLimit() && code.getViews() <= 0) {
            System.out.println("DEBUG nullIfExpired Views Check: Deleting: " + code.toString());
            codeRepository.delete(code);
            return null;
        }
        if (code.hasViewsLimit()) {
            code.decreaseViewCount();
            codeRepository.save(code);
        }
        if (code.getExpiryTime() != null) {
            code.setRemainingTime((int) Duration.between(LocalDateTime.now(), code.getExpiryTime()).toSeconds());
            codeRepository.save(code);
        }
        return code;
    }
}
