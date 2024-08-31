package pravna.com.myapp.service.extended.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pravna.com.myapp.repository.extended.PresudaExtendedRepository;
import pravna.com.myapp.service.dto.PresudaDTO;
import pravna.com.myapp.service.extended.*;
import pravna.com.myapp.service.impl.PresudaServiceImpl;
import pravna.com.myapp.service.mapper.PresudaMapper;

@Service
@Primary
public class PresudaExtendedServiceImpl extends PresudaServiceImpl implements PresudaExtendedService {

    private final Logger log = LoggerFactory.getLogger(PresudaServiceImpl.class);

    private final PresudaExtendedRepository presudaRepository;

    private final PresudaMapper presudaMapper;

    private final OptuzeniExtendedService optuzeniService;

    private final OsobaExtendedService osobaService;

    private final RadnjaPresudeExtendedService radnjaPresudeService;

    private final SudExtendedService sudService;

    private final OptuznicaExtendedService optuznicaService;

    public PresudaExtendedServiceImpl(
        PresudaExtendedRepository presudaRepository,
        PresudaMapper presudaMapper,
        OptuzeniExtendedService optuzeniService,
        OsobaExtendedService osobaService,
        RadnjaPresudeExtendedService radnjaPresudeService,
        SudExtendedService sudService,
        OptuznicaExtendedService optuznicaService
    ) {
        super(presudaRepository, presudaMapper);
        this.presudaRepository = presudaRepository;
        this.presudaMapper = presudaMapper;
        this.optuzeniService = optuzeniService;
        this.osobaService = osobaService;
        this.radnjaPresudeService = radnjaPresudeService;
        this.sudService = sudService;
        this.optuznicaService = optuznicaService;
    }

    @Override
    public PresudaDTO save(PresudaDTO presudaDTO) {
        if (presudaDTO.getRadnja().getId() == null) presudaDTO.setRadnja(radnjaPresudeService.save(presudaDTO.getRadnja()));
        if (presudaDTO.getOptuzeni().getId() == null) presudaDTO.setOptuzeni(optuzeniService.save(presudaDTO.getOptuzeni()));
        if (presudaDTO.getOptuznica().getId() == null) presudaDTO.setOptuznica(optuznicaService.save(presudaDTO.getOptuznica()));
        if (presudaDTO.getSud().getId() == null) presudaDTO.setSud(sudService.save(presudaDTO.getSud()));
        if (presudaDTO.getBranilac().getId() == null) presudaDTO.setBranilac(osobaService.save(presudaDTO.getBranilac()));
        if (presudaDTO.getSudija().getId() == null) presudaDTO.setSudija(osobaService.save(presudaDTO.getSudija()));
        if (presudaDTO.getZapisnicar().getId() == null) presudaDTO.setZapisnicar(osobaService.save(presudaDTO.getZapisnicar()));
        if (presudaDTO.getTuzilac().getId() == null) presudaDTO.setTuzilac(osobaService.save(presudaDTO.getTuzilac()));
        if (presudaDTO.getOsteceni().getId() == null) presudaDTO.setOsteceni(osobaService.save(presudaDTO.getOsteceni()));
        super.save(presudaDTO);

        return presudaDTO;
    }
}
