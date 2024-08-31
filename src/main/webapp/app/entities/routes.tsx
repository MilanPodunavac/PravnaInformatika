import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Presuda from './presuda';
import Osoba from './osoba';
import Optuzeni from './optuzeni';
import RadnjaPresude from './radnja-presude';
import Povreda from './povreda';
import Kazna from './kazna';
import Zakon from './zakon';
import ClanZakona from './clan-zakona';
import Sud from './sud';
import Optuznica from './optuznica';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="presuda/*" element={<Presuda />} />
        <Route path="osoba/*" element={<Osoba />} />
        <Route path="optuzeni/*" element={<Optuzeni />} />
        <Route path="radnja-presude/*" element={<RadnjaPresude />} />
        <Route path="povreda/*" element={<Povreda />} />
        <Route path="kazna/*" element={<Kazna />} />
        <Route path="zakon/*" element={<Zakon />} />
        <Route path="clan-zakona/*" element={<ClanZakona />} />
        <Route path="sud/*" element={<Sud />} />
        <Route path="optuznica/*" element={<Optuznica />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
