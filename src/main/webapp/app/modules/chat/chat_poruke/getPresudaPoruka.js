export const getPresudaPorukaOld = `U narednoj poruci će se nalaziti neispravan i potencijalno anonimizovan tekst presude koja se bavi krivičnim delima ubistva i pokušaja ubistava. Povratna vrednost treba da ti bude samo naredni podaci u JSON formatu:
{
id: Identifikacioni broj presude,
tip: Tip presude, obično obeležena slovima unutar identifikacionog broja presude. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa TipPresude, prema slovima u presudi i oznakama tipova presuda unutar zagrada,
broj: Broj presude, obično obeležen brojem ispred kose crte unutar identifikacionog broja presude
godina: Godina presude, obeležena brojem nakon kose crte unutar identifikacionog broja presude. Obrati pažnju da se nekada koristi skraćena verzija godine gde su pišu samo poslednje dve cifre godine, i da godina presude odgovara godini unutar datuma presude
sud: {
naziv: Naziv suda u kom je izvršeno suđenje
},
datum: Datum suđenja,
datum_objave: Datum objave presude,
optuznica: Identifikacioni broj optužnice,
datum_optuznice: Datum podizanja optužnice,
datum_pritvora: Datum početka pritvora optuženog. Ukoliko nije bio pritvoren pre suđenja, vrati null,
pokusaj: Da li je izvršeno ubistvo ili pokušaj ubistva. Vrati ovu vrednost u boolean tipu,
krivica: Da li je optuženi kriv za krivično delo za koje se tereti. Vrati ovu vrednost u boolean formatu,
nacin: Način krivičnog dela koje je izvršeno. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa TipUbistva,
radnja: {
vreme_radnje: Datum izvršenja krivičnog dela za koje se tereti,
mesto_radnje: Mesto izvršenja krivičnog dela za koje se tereti,
vreme_smrti: Ukoliko je nastupila smrt, datum smrti osobe za čije se ubistvo tereti optuženi. Ukoliko nije naveden poseban datum, pretpostavi da je identičan kao datum izvršenja radnje,
mesto_smrti: Ukoliko je nastupila smrt, mesto smrti osobe za čije se ubistvo tereti optuženi. Ukoliko nije navedeno posebno mesto smrti, pretpostavi da je identičan kao datum izvršenja radnje,
povrede: Povrede nanesene oštećenom od strane optuženog. Podatke vrati u sledećem formatu {
oruzje: Naziv oružja korišćen da nanese povrede,
deoTela: Deo tela gde je nanesena povreda. Ukoliko je povređeno više delova tela, vrati podatke kao pojedinačne povrede,
povrede: Opis nanesene povrede, sem naziva oružja i dela tela koji je povređen,
},
},
optuzeni: {
ime: Ime optuzenog. Ukoliko je ime anonimizovano, vrati anonimizovanu verziju,
jmbg: JMBG optuzenog. Ukoliko je JMBG anonimizovan, vrati prazan string,
ime_oca: Ime oca optuzenog. Ukoliko je ime oca anonimizovano, vrati anonimizovanu verziju,
ime_majke:  Ime majke optuzenog. Uključi i njeno rođeno prezime, ukoliko je dostupno. Ukoliko je ime majke anonimizovano, vrati anonimizovanu verziju,
pol: Pol optuženog, velikim slovima. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa Pol,
datum_rodjenja: Datum rođenja optuženog. Ukoliko je datum rođenja anonimizovan, vrati null,
mesto_rodjenja: Mesto rođenja optuženog. Ne uključuj državu rođenja, samo mesto
država_rodjenja: Država rođenja optuženog,
prebivaliste: Prebivalište optuženog. Ukoliko je prebivalište anonimizovano, vrati anonimizovanu verziju prebivališta ukoliko je dostupno,
bracni_status: Bračni status optuženog. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa BracniStatus,
broj_dece: Broj dece optuženog,
broj_maloletne_dece: Broj maloletne dece optuženog,
imovinsko_stanje: Imovinsko stanje optuženog. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa ImovinskoStanje,
obrazovanje: Tip obrazovanja optuženog. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa TipObrazovanja,
zaposlenje: Tip zaposlenja optuženog. Ukoliko je nezaposlen, vratiti prazan string.
mesto_zaposlenja: Mesto zaposlenja optuženog. Ukoliko je nezaposlen, vratiti prazan string
presude: Prethodne presude optuženog. Presude su napisane zajedno sa ostalim informacijama optuženog, i pokušaj da vratiš sve presude koje nađeš iako su potencijalno anonimizovane ili nekompletne. Ukoliko je neosuđivan, vrati prazan niz. Podatke vrati u sledećem formatu {
id: Identifikacioni broj presude,
tip: Tip presude, obično obeležena slovima unutar identifikacionog broja presude. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa TipPresude, prema slovima u presudi i oznakama tipova presuda unutar zagrada,
broj: Broj presude, obično obeležen brojem ispred kose crte unutar identifikacionog broja presude,
godina: Godina presude, obeležena brojem nakon kose crte unutar identifikacionog broja presude. Obrati pažnju da se nekada koristi skraćena verzija godine gde su pišu samo poslednje dve cifre godine, i da godina presude odgovara godini unutar datuma presude,
sud: {
naziv: Naziv suda u kom je izvršeno suđenje
},
clanovi_zakona: Svi članovi spomenuti u presudi, po kojima je osuđen optuženi ili određena kazna. U ovim presudama se obično spominje samo jedan član zakona. Podatke vrati u sledećem formatu {
broj: Broj člana zakona, vrati podatak kao broj,
zakon: Naziv zakona kom pripada član zakona. Nekada su zakoni navedeni u skraćenom formatu, ali uvek vrati ovaj podatak kao pun naziv zakona. Ukoliko ne postoje informacije o punom nazivu zakona, neka značenja skraćenica su kasnije u tekstu obeležena sa ImenaZakona
}
kazne: Kazne presude ukoliko je uspostavljena krivica optuženog. U ovim presudama obično postoji jedna kazna. Podatke vrati u sledećem formatu {
tip: Tip kazne. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa TipKazne,
duzina: U slučaju zatvorske ili uslovne kazne, vremenska dužina kazne. Podatak vratiti u formi broja, i izmeren u broju meseci kazne
uracunavanjePritvora: U slučaju zatvorske ili uslovne kazne, da li se uračunava vreme već provedeno u pritvoru pre suđenja gde je objavljena ova presuda
kolicinaNovca: U slučaju novčane kazne, količina novca koja se nalaže za plaćanje. Podatak vratiti u formi broja
primalacNovca: U slučaju novčane kazne, primalac novca koju optuženi treba da plati. Ukoliko postoji više novčanih kazni sa više primalaca novca, te podatke vratiti kao pojedinačne kazne. Kazne na ime paušala takođe obeleži kao posebnog primaoca novca
nazivImovine: U slučaju oduzimanja imovine, naziv imovine koja se oduzima. Ukoliko se oduzima više od jedne imovine, te podatke vratiti kao pojedinačne kazne. Obično se oduzima oružje koje je korišćeno u krivičnom delu, kao i dodatno oružje nađeno kod optuženog, pa obrati pažnju i na te podatke
}
}
}
clanovi_zakona: Svi članovi spomenuti u presudi, po kojima je osuđen optuženi ili određena kazna. Podatke vrati u sledećem formatu {
broj: Broj člana zakona, vrati podatak kao broj,
zakon: Naziv zakona kom pripada član zakona. Nekada su zakoni navedeni u skraćenom formatu, ali uvek vrati ovaj podatak kao pun naziv zakona. Ukoliko ne postoje informacije o punom nazivu zakona, neka značenja skraćenica su kasnije u tekstu obeležena sa ImenaZakona
}
sudija: {
ime: Ime sudije predsednika veća na suđenju,
pol: Pol sudije predsednika veća na suđenju, velikim slovima. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa Pol,
},
vece: Sudije članovi veća, koji nisu predsednici. Podatke vrati u sledećem formatu {
ime: Ime sudije koji je član veća,
pol: Pol sudije koji je član veća, velikim slovima. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa Pol,
}
zapisnicar: {
ime: Ime zapisničara prisutnog na suđenju,
pol: Pol zapisničara, velikim slovima. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa Pol,
},
tuzilac: {
ime: Ime tužioca prisutnog na suđenju,
pol: Pol tužioca, velikim slovima. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa Pol,
},
branilac: {
ime: Ime branioca optuženog prisutnog na suđenju,
pol: Pol branioca, velikim slovima. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa Pol,
},
kazne: Kazne presude ukoliko je uspostavljena krivica optuženog. Podatke vrati u sledećem formatu {
tip: Tip kazne. Izaberi jednu od mogućnosti koja će kasnije u tekstu biti obeležena sa TipKazne,
duzina: U slučaju zatvorske ili uslovne kazne, vremenska dužina kazne. Podatak vratiti u formi broja, i izmeren u broju meseci kazne
uracunavanjePritvora: U slučaju zatvorske ili uslovne kazne, da li se uračunava vreme već provedeno u pritvoru pre suđenja gde je objavljena ova presuda
kolicinaNovca: U slučaju novčane kazne, količina novca koja se nalaže za plaćanje. Podatak vratiti u formi broja
primalacNovca: U slučaju novčane kazne, primalac novca koju optuženi treba da plati. Ukoliko postoji više novčanih kazni sa više primalaca novca, te podatke vratiti kao pojedinačne kazne. Kazne na ime paušala takođe obeleži kao posebnog primaoca novca
nazivImovine: U slučaju oduzimanja imovine, naziv imovine koja se oduzima. Ukoliko se oduzima više od jedne imovine, te podatke vratiti kao pojedinačne kazne. Obično se oduzima oružje koje je korišćeno u krivičnom delu, kao i dodatno oružje nađeno kod optuženog, pa obrati pažnju i na te podatke
}
}

TipPresude: PRVOSTEPENI_KRIVICNI_PREDMET (K), SPECIJALNI_KRIVICNI_PREDMET (K-S), PREDMET_SUDIJE_ZA_ISTRAGU (Ki), SPECIJALNI_ISTRAZNI_PREDMET (Ki-S), PRIPREMNI_POSTUPAK_PREMA_MALOLETNICIMA (Kim), KRIVICNI_POSTUPAK_PREMA_MALOLETNICIMA (Km), PREDMETI_POMILOVANJA (Kp), RAZNA_KRIVICNA_STVAR (Kr),  KRIVICNI_SPECIJALNI (Kr-S), ISTRAZNA_RADNJA (Kri), DISCIPLINSKA_ZALBA (Dž), ZASTITA_ZAKONITOSTI_U_PARNICNOM_POSTUPKU (Gzz), DRUGOSTEPENSKI_GRADJANSKI_PREDMET (Gž), PREDMET_IZVRSENJA (I), PREDMET_O_IZVRSENJU_PREKRSAJNIH_SANKCIJA (IPS), IZVRSENJE_PO_VERODOSTOJNOJ_ISPRAVI (IV), KONTROLNI_ZAHTEV (IV-Su-2), IZUZECE (IV-Su-3), IZVRSENJE_KRIVICNIH_SANKCIJA (Iks)
TipUbistva: SA_PREDUMISLJAJEM, BEZ_PREDUMISLJAJA, IZ_NEHATA, NAKON_PORODJAJA, IZ_SAMILOSTI
Pol: MUSKI, ZENSKI
BracniStatus: VAN_BRAKA, U_BRAKU, RAZVOD, SMRT_U_BRAKU
ImovinskoStanje: LOSE, SREDNJE, DOBRO
TipObrazovanja: OSNOVNA_SKOLA, SREDNJA_SKOLA, VISA_SKOLA, FAKULTET, NEOBRAZOVAN
TipKazne: ZATVORSKA_KAZNA, USLOVNA_KAZNA, NOVCANA_KAZNA, ODUZIMANJE_IMOVINE

ImenaZakona: 
KZ = Krivični zakonik
ZKP = Zakon o krivičnom postupku`;

export const getPresudaPoruka = `
Dobijaš ulogu ekstraktora informacija iz neispravnog i potencijalno anonimitovanog teksta presude koja se bavi krivičnim delima ubistva i pokušaja ubistava.
Tvoj zadatak je da ispraviš nepravilnosti u tekstu, i iz ispravljenog teksta izvučeš relevantne podatke specificirane u šemi podataka.
Tekst presude može biti veći od dozvoljene veličine jedne poruke, tako da je moguće da će biti poslata u više poruka, uzmi u obzir sve poruke u kojima se nalazi tekst presude.
Reši dvosmislenosti i konflikte informacija u skladu sa datom šemom podataka i koristeći mogućnosti modela da što bolje razume smisao ulaznih informacija.
Izvučeni podaci moraju biti validni, u skladu sa specificiranim formatima u šemi podataka, i gramatički ispravni.
Podatke pretvori u strukturu specificiranu u šemi podataka, kao JSON objekat.
Striktno prati specificiranu šemu kako bi povratni JSON objekat bio spreman za poziv programske funkcije.
Preciznost povratnih podataka su od najveće važnosti za ovaj zadatak, potrudi se da su podaci što realnije i detaljnije predstavljaju činjenično stanje opisano u tekstu presude.
`;

export const getPresudaSema = {
  name: 'get_presuda_info',
  description:
    'Potrebno je izvući informacije iz neispravanog i potencijalno anonimizovanog teksta presude koja se bavi krivičnim delima ubistva i pokušaja ubistava. Model bi trebalo da iz ispravljenog teksta izvuče relevantne informacije o presudi, krivičnom delu, optuženom itd.',
  parameters: {
    type: 'object',
    properties: {
      kod: {
        type: 'string',
        description: 'Identifikacioni broj presude.',
      },
      tip: {
        type: 'string',
        enum: [
          'K - PRVOSTEPENI_KRIVICNI_PREDMET',
          'K-S - SPECIJALNI_KRIVICNI_PREDMET',
          'Ki - PREDMET_SUDIJE_ZA_ISTRAGU ',
          'Ki-S - SPECIJALNI_ISTRAZNI_PREDMET',
          'Kim - PRIPREMNI_POSTUPAK_PREMA_MALOLETNICIMA',
          'Km - KRIVICNI_POSTUPAK_PREMA_MALOLETNICIMA',
          'Kp - PREDMETI_POMILOVANJA',
          'Kr - RAZNA_KRIVICNA_STVAR',
          'Kr-S - KRIVICNI_SPECIJALNI',
          'Kri - ISTRAZNA_RADNJA',
          'Dž - DISCIPLINSKA_ZALBA',
          'Gzz - ZASTITA_ZAKONITOSTI_U_PARNICNOM_POSTUPKU',
          'Gž - DRUGOSTEPENSKI_GRADJANSKI_PREDMET',
          'I - PREDMET_IZVRSENJA',
          'IPS - PREDMET_O_IZVRSENJU_PREKRSAJNIH_SANKCIJA',
          'IV - IZVRSENJE_PO_VERODOSTOJNOJ_ISPRAVI',
          'IV-Su-2 - KONTROLNI_ZAHTEV',
          'IV-Su-3 - IZUZECE',
          'Iks - IZVRSENJE_KRIVICNIH_SANKCIJA',
        ],
        description:
          'Tip presude, obično obeležena slovima unutar identifikacionog broja presude u skraćenoj formi. Obrati pažnju da se verzije tipova presuda nalaze na početku vrednosti enumeracija.',
      },
      broj: {
        type: 'number',
        description: 'Broj presude, obično obeležen brojem ispred kose crte unutar identifikacionog broja presude.',
      },
      godina: {
        type: 'string',
        description:
          'Godina presude, obeležena brojem nakon kose crte unutar identifikacionog broja presude. Obrati pažnju da se nekada koristi skraćena verzija godine gde su pišu samo poslednje dve cifre godine, i da godina presude odgovara godini unutar datuma presude.',
      },
      sud: {
        type: 'object',
        description: 'Sud u kom je izvršeno suđenje',
        properties: {
          naziv: {
            type: 'string',
            description: 'Naziv suda u kom je izvršeno suđenje',
          },
          mesto: {
            type: 'string',
            description: 'Mesto suda u kom je izvršeno suđenje',
          },
        },
        required: ['naziv', 'mesto'],
      },
      datum: {
        type: 'string',
        description: "Datum objave presude, u formatu 'YYYY-MM-DD'.",
      },
      optuznica: {
        type: 'object',
        description: 'Optužnica podneta prema optuženom.',
        properties: {
          kod: {
            type: 'string',
            description: 'Identifikacioni broj optužnice.',
          },
          datum: {
            type: 'string',
            description: "Datum podizanja optužnice, u formatu 'YYYY-MM-DD'.",
          },
          ustanova: {
            type: 'string',
            description: 'Ustanova koja je podigla optužnicu.',
          },
        },
        required: ['id', 'datum'],
      },
      sudija: {
        type: 'object',
        properties: {
          ime: {
            type: 'string',
            description: 'Ime sudije predsednika veća na suđenju.',
          },
          pol: {
            type: 'string',
            enum: ['MUSKI', 'ZENSKI'],
            description: 'Pol sudije predsednika veća na suđenju.',
          },
        },
        required: ['ime', 'pol'],
      },
      vece: {
        type: 'array',
        description: 'Sudije članovi veća, koji nisu predsednici.',
        items: {
          type: 'object',
          properties: {
            ime: {
              type: 'string',
              description: 'Ime sudije koji je član veća.',
            },
            pol: {
              type: 'string',
              enum: ['MUSKI', 'ZENSKI'],
              description: 'Pol sudije koji je član veća.',
            },
          },
          required: ['ime', 'pol'],
        },
      },
      'zapisnicar: ': {
        type: 'object',
        properties: {
          ime: {
            type: 'string',
            description: 'Ime zapisničara prisutnog na suđenju.',
          },
          pol: {
            type: 'string',
            enum: ['MUSKI', 'ZENSKI'],
            description: 'Pol zapisničara prisutnog na suđenju.',
          },
        },
        required: ['ime', 'pol'],
      },
      tuzilac: {
        type: 'object',
        properties: {
          ime: {
            type: 'string',
            description: 'Ime tužioca prisutnog na suđenju.',
          },
          pol: {
            type: 'string',
            enum: ['MUSKI', 'ZENSKI'],
            description: 'Pol tužioca prisutnog na suđenju.',
          },
        },
        required: ['ime', 'pol'],
      },
      branilac: {
        type: 'object',
        properties: {
          ime: {
            type: 'string',
            description: 'Ime branioca optuženog prisutnog na suđenju.',
          },
          pol: {
            type: 'string',
            enum: ['MUSKI', 'ZENSKI'],
            description: 'Pol branioca optuženog prisutnog na suđenju.',
          },
        },
        required: ['ime', 'pol'],
      },
      datum_pritvora: {
        type: 'string',
        description:
          "Datum početka pritvora optuženog, u formatu 'YYYY-MM-DD'. Ukoliko nije bio pritvoren pre suđenja, vrati vrednost 'null'",
      },
      pokusaj: {
        type: 'boolean',
        description: 'Da li je izvršeno ubistvo ili pokušaj ubistva.',
      },
      krivica: {
        type: 'boolean',
        description: 'Da li je optuženi kriv za krivično delo za koje se tereti.',
      },
      nacin: {
        type: 'string',
        enum: ['SA_PREDUMISLJANJEM', 'BEZ_PREDUMISLJAJA', 'IZ_NEHATA', 'NAKON_PORODJAJA', 'IZ_SAMILOSTI'],
        description: 'Način krivičnog dela koje je izvršeno.',
      },
      radnja: {
        type: 'object',
        properties: {
          osteceni: {
            type: 'object',
            description: 'Žrtva za krivično delo za koje se tereti optuženi',
            properties: {
              ime: {
                type: 'string',
                description: 'Ime oštećenog, žrtve krivičnog dela optuženog.',
              },
              pol: {
                type: 'string',
                enum: ['MUSKI', 'ZENSKI'],
                description: 'Pol oštećenog, žrtve krivičnog dela optuženog.',
              },
            },
            required: ['ime', 'pol'],
          },
          vreme_radnje: {
            type: 'string',
            description: "Datum izvršenja krivičnog dela za koje se optuženi tereti, u formatu 'YYYY-MM-DD'.",
          },
          mesto_radnje: {
            type: 'string',
            description: 'Mesto izvršenja krivičnog dela za koje se optuženi tereti.',
          },
          vreme_smrti: {
            type: 'string',
            description:
              "Ukoliko je nastupila smrt, datum smrti osobe za čije se ubistvo tereti optuženi, u formatu 'YYYY-MM-DD'. Ukoliko nije naveden poseban datum, pretpostavi da je identičan kao datum izvršenja radnje",
          },
          mesto_smrti: {
            type: 'string',
            description:
              'Ukoliko je nastupila smrt, mesto smrti osobe za čije se ubistvo tereti optuženi. Ukoliko nije navedeno posebno mesto smrti, pretpostavi da je identičan kao mesto izvršenja radnje.',
          },
          povrede: {
            type: 'array',
            description:
              'Povrede za koje je optuženi okrivljen, nanete oštećenom. Povrede koje su nanete optuženom od strane žrtve, ili koje su nanete nekoj drugoj osobi spomenutoj u presudi, ne uključuj. ',
            items: {
              type: 'object',
              properties: {
                oruzje: {
                  type: 'string',
                  description: 'Naziv oružja korišćen da nanese povrede.',
                },
                deo_tela: {
                  type: 'string',
                  description:
                    'Deo tela gde je nanesena povreda. Ukoliko je povređeno više delova tela, vrati podatke kao pojedinačne povrede.',
                },
                povrede: {
                  type: 'string',
                  description: 'Opis nanesene povrede, sem naziva oružja i dela tela koji je povređen.',
                },
              },
              required: ['oruzje', 'deo_tela', 'povrede'],
            },
          },
        },
        required: ['osteceni', 'vreme_radnje', 'mesto_radnje', 'povrede'],
      },
      optuzeni: {
        type: 'object',
        properties: {
          ime: {
            type: 'string',
            description: 'Ime optuzenog. Ukoliko je ime anonimizovano, vrati anonimizovanu verziju.',
          },
          jmbg: {
            type: 'string',
            description: 'JMBG optuzenog. Ukoliko je JMBG anonimizovan, vrati prazan string.',
          },
          ime_oca: {
            type: 'string',
            description: 'Ime oca optuzenog. Ukoliko je ime oca anonimizovano, vrati anonimizovanu verziju.',
          },
          ime_majke: {
            type: 'string',
            description:
              'Ime majke optuzenog. Uključi i njeno rođeno prezime, ukoliko je dostupno. Ukoliko je ime majke anonimizovano, vrati anonimizovanu verziju.',
          },
          pol: {
            type: 'string',
            enum: ['MUSKI', 'ZENSKI'],
            description: 'Pol optuženog.',
          },
          datum_rodjenja: {
            type: 'string',
            description: "Datum rođenja optuženog, u formatu 'YYYY-MM-DD'. Ukoliko je datum rođenja anonimizovan, vrati null.",
          },
          mesto_rodjenja: {
            type: 'string',
            description: 'Mesto rođenja optuženog. Ne uključuj državu rođenja, samo mesto.',
          },
          drzava_rodjenja: {
            type: 'string',
            description: 'Država rođenja optuženog.',
          },
          prebivaliste: {
            type: 'string',
            description:
              'Prebivalište optuženog. Ukoliko je prebivalište anonimizovano, vrati anonimizovanu verziju prebivališta ukoliko je dostupno.',
          },
          državljanstvo: {
            type: 'string',
            description:
              'Državljanstvo optuženog. Vrati vrednost državljanstva u vidu prideva (npr. crnogorsko umesto državljanin Crne Gore).',
          },
          bracni_status: {
            type: 'string',
            enum: ['VAN_BRAKA', 'U_BRAKU', 'PREKINUT_BRAK', 'SMRT_U_BRAKU'],
            description: 'Bračni status optuženog.',
          },
          broj_dece: {
            type: 'number',
            description: 'Broj dece optuženog.',
          },
          broj_maloletne_dece: {
            type: 'number',
            description: 'Broj maloletne dece optuženog.',
          },
          imovinsko_stanje: {
            type: 'string',
            enum: ['LOSE', 'SREDNJE', 'DOBRO'],
            description: 'Imovinsko stanje optuženog.',
          },
          obrazovanje: {
            type: 'string',
            enum: ['NEOBRAZOVAN', 'OSNOVNA_SKOLA', 'SREDNJA_SKOLA', 'VISOKA_SKOLA', 'FAKULTET'],
            description: 'Tip obrazovanja optuženog.',
          },
          zaposlenje: {
            type: 'string',
            description: 'Tip zaposlenja optuženog. Ukoliko je nezaposlen, vratiti prazan string.',
          },
          mesto_zaposlenja: {
            type: 'string',
            description: 'Mesto zaposlenja optuženog. Ukoliko je nezaposlen, vratiti prazan string.',
          },
          presude: {
            type: 'array',
            description:
              'Prethodne presude optuženog. Presude su napisane zajedno sa ostalim informacijama optuženog, i pokušaj da vratiš sve presude koje nađeš iako su potencijalno anonimizovane ili nekompletne, obično u vidu nedostajućih delova identifikacionog broja presude. Ukoliko je neosuđivan, vrati prazan niz.',
            items: {
              type: 'object',
              properties: {
                id: {
                  type: 'string',
                  description: 'Identifikacioni broj presude.',
                },
                tip: {
                  type: 'string',
                  enum: [
                    'K - PRVOSTEPENI_KRIVICNI_PREDMET',
                    'K-S - SPECIJALNI_KRIVICNI_PREDMET',
                    'Ki - PREDMET_SUDIJE_ZA_ISTRAGU ',
                    'Ki-S - SPECIJALNI_ISTRAZNI_PREDMET',
                    'Kim - PRIPREMNI_POSTUPAK_PREMA_MALOLETNICIMA',
                    'Km - KRIVICNI_POSTUPAK_PREMA_MALOLETNICIMA',
                    'Kp - PREDMETI_POMILOVANJA',
                    'Kr - RAZNA_KRIVICNA_STVAR',
                    'Kr-S - KRIVICNI_SPECIJALNI',
                    'Kri - ISTRAZNA_RADNJA',
                    'Dž - DISCIPLINSKA_ZALBA',
                    'Gzz - ZASTITA_ZAKONITOSTI_U_PARNICNOM_POSTUPKU',
                    'Gž - DRUGOSTEPENSKI_GRADJANSKI_PREDMET',
                    'I - PREDMET_IZVRSENJA',
                    'IPS - PREDMET_O_IZVRSENJU_PREKRSAJNIH_SANKCIJA',
                    'IV - IZVRSENJE_PO_VERODOSTOJNOJ_ISPRAVI',
                    'IV-Su-2 - KONTROLNI_ZAHTEV',
                    'IV-Su-3 - IZUZECE',
                    'Iks - IZVRSENJE_KRIVICNIH_SANKCIJA',
                  ],
                  description:
                    'Tip presude, obično obeležena slovima unutar identifikacionog broja presude u skraćenoj formi. Obrati pažnju da se verzije tipova presuda nalaze na početku vrednosti enumeracija.',
                },
                broj: {
                  type: 'number',
                  description:
                    'Godina presude, obeležena brojem nakon kose crte unutar identifikacionog broja presude. Obrati pažnju da se nekada koristi skraćena verzija godine gde su pišu samo poslednje dve cifre godine, i da godina presude odgovara godini unutar datuma presude.',
                },
                godina: {
                  type: 'string',
                  description: 'Identifikacioni broj presude.',
                },
                sud: {
                  type: 'object',
                  properties: {
                    naziv: {
                      type: 'string',
                      description: 'Naziv suda u kom je izvršeno suđenje',
                    },
                    mesto: {
                      type: 'string',
                      description: 'Mesto suda u kom je izvršeno suđenje',
                    },
                  },
                  required: ['naziv', 'mesto'],
                },
                datum: {
                  type: 'string',
                  description: "Datum suđenja, u formatu 'YYYY-MM-DD'.",
                },
                clanovi_zakona: {
                  type: 'array',
                  description:
                    'Svi članovi spomenuti u presudi, po kojima je osuđen optuženi ili određena kazna. U prethodnim presudama optuženog se obično spominje jedan član zakona.',
                  items: {
                    type: 'object',
                    properties: {
                      broj: {
                        type: 'number',
                        description: 'Broj člana zakona.',
                      },
                      zakon: {
                        type: 'string',
                        description:
                          'Naziv zakona kom pripada član zakona. Nekada su zakoni navedeni u skraćenom formatu, ali uvek vrati ovaj podatak kao pun naziv zakona. Potrudi se da na nivou ove presude, imena zakona budu konzistentna (npr. Krivični zakon, Krivični zakonik, Krivični zakon CG, Krivični zakon Crne Gore su vrlo verovatno isti zakoni).',
                      },
                    },
                    required: ['broj', 'zakon'],
                  },
                },
                kazne: {
                  type: 'array',
                  description:
                    'Kazne presude ukoliko je uspostavljena krivica optuženog. U prethodnim presudama optuženog se obično spominje jedna kazna.',
                  items: {
                    type: 'object',
                    properties: {
                      tip: {
                        type: 'string',
                        enum: ['ZATVORSKA_KAZNA', 'USLOVNA_KAZNA', 'NOVCANA_KAZNA', 'IMOVINSKA_KAZNA'],
                        description: 'Tip kazne.',
                      },
                      duzina: {
                        type: 'number',
                        description:
                          'U slučaju zatvorske ili uslovne kazne, vremenska dužina kazne. Obrati pažnju da dužinu kazne sabereš ukoliko je napisana u različitim jedinicama, i konvertuješ u mesece.',
                      },
                      uracunavanje_pritvora: {
                        type: 'string',
                        description:
                          'U slučaju zatvorske ili uslovne kazne, da li se uračunava vreme već provedeno u pritvoru pre suđenja gde je objavljena ova presuda.',
                      },
                      kolicina_novca: {
                        type: 'string',
                        description: ' U slučaju novčane kazne, količina novca (u evrima) koja se nalaže za plaćanje.',
                      },
                      primalac_novca: {
                        type: 'string',
                        description:
                          'U slučaju novčane kazne, primalac novca koju optuženi treba da plati. Ukoliko postoji više novčanih kazni sa više primalaca novca, te podatke vratiti kao pojedinačne kazne. Kazne na ime paušala takođe obeleži kao posebnog primaoca novca.',
                      },
                      naziv_imovine: {
                        type: 'string',
                        description:
                          'U slučaju oduzimanja imovine, naziv imovine koja se oduzima. Ukoliko se oduzima više od jedne imovine, te podatke vratiti kao pojedinačne kazne. Obično se oduzima oružje koje je korišćeno u krivičnom delu, kao i dodatno oružje nađeno kod optuženog, pa obrati pažnju i na te podatke.',
                      },
                    },
                    required: ['tip'],
                  },
                },
              },
            },
          },
        },
        required: ['ime', 'pol', 'drzavljanstvo', 'bracni_status', 'imovinsko_stanje', 'obrazovanje'],
      },
      clanovi_zakona: {
        type: 'array',
        description:
          'Svi članovi spomenuti u presudi, po kojima je osuđen optuženi ili određena kazna. Obrati pažnju i na članove koji se spominju kao članovi koje sud primenjuje prilikom utvrđivanja kazne ili osuđivanja. Obrati pažnju i na članove koji su u vezi sa drugim članovima u presudi.',
        items: {
          type: 'object',
          properties: {
            broj: {
              type: 'number',
              description: 'Broj člana zakona.',
            },
            zakon: {
              type: 'string',
              description:
                'Naziv zakona kom pripada član zakona. Nekada su zakoni navedeni u skraćenom formatu, ali uvek vrati ovaj podatak kao pun naziv zakona. Potrudi se da na nivou ove presude, imena zakona budu konzistentna (npr. Krivični zakon, Krivični zakonik, Krivični zakon CG, Krivični zakon Crne Gore su vrlo verovatno isti zakoni).',
            },
          },
          required: ['broj', 'zakon'],
        },
      },
      kazne: {
        type: 'array',
        description: 'Kazne presude ukoliko je uspostavljena krivica optuženog.',
        items: {
          type: 'object',
          properties: {
            tip: {
              type: 'string',
              enum: ['ZATVORSKA_KAZNA', 'USLOVNA_KAZNA', 'NOVCANA_KAZNA', 'IMOVINSKA_KAZNA'],
              description: 'Tip kazne.',
            },
            duzina: {
              type: 'number',
              description:
                'U slučaju zatvorske ili uslovne kazne, vremenska dužina kazne. Obrati pažnju da dužinu kazne sabereš ukoliko je napisana u različitim jedinicama, i konvertuješ u mesece.',
            },
            uracunavanje_pritvora: {
              type: 'string',
              description:
                'U slučaju zatvorske ili uslovne kazne, da li se uračunava vreme već provedeno u pritvoru pre suđenja gde je objavljena ova presuda.',
            },
            kolicina_novca: {
              type: 'string',
              description: ' U slučaju novčane kazne, količina novca (u evrima) koja se nalaže za plaćanje.',
            },
            primalac_novca: {
              type: 'string',
              description:
                'U slučaju novčane kazne, primalac novca koju optuženi treba da plati. Ukoliko postoji više novčanih kazni sa više primalaca novca, te podatke vratiti kao pojedinačne kazne. Kazne na ime paušala takođe obeleži kao posebnog primaoca novca.',
            },
            naziv_imovine: {
              type: 'string',
              description:
                'U slučaju oduzimanja imovine, naziv imovine koja se oduzima. Ukoliko se oduzima više od jedne imovine, te podatke vratiti kao pojedinačne kazne. Obično se oduzima oružje koje je korišćeno u krivičnom delu, kao i dodatno oružje nađeno kod optuženog, pa obrati pažnju i na te podatke.',
            },
          },
          required: ['tip'],
        },
      },
    },
    required: [
      'kod',
      'datum',
      'datum_objave',
      'tip',
      'broj',
      'godina',
      'optuznica',
      'pokusaj',
      'krivica',
      'nacin',
      'radnja',
      'optuzeni',
      'clanovi_zakona',
      'sudija',
      'tuzilac',
      'branilac',
      'zapisnicar',
      'vece',
    ],
  },
};
