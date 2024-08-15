import presuda from 'app/entities/presuda/presuda.reducer';
import osoba from 'app/entities/osoba/osoba.reducer';
import optuzeni from 'app/entities/optuzeni/optuzeni.reducer';
import radnjaPresude from 'app/entities/radnja-presude/radnja-presude.reducer';
import povreda from 'app/entities/povreda/povreda.reducer';
import kazna from 'app/entities/kazna/kazna.reducer';
import zakon from 'app/entities/zakon/zakon.reducer';
import clanZakona from 'app/entities/clan-zakona/clan-zakona.reducer';
import sud from 'app/entities/sud/sud.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  presuda,
  osoba,
  optuzeni,
  radnjaPresude,
  povreda,
  kazna,
  zakon,
  clanZakona,
  sud,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
