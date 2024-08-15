import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ClanZakona from './clan-zakona';
import ClanZakonaDetail from './clan-zakona-detail';
import ClanZakonaUpdate from './clan-zakona-update';
import ClanZakonaDeleteDialog from './clan-zakona-delete-dialog';

const ClanZakonaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ClanZakona />} />
    <Route path="new" element={<ClanZakonaUpdate />} />
    <Route path=":id">
      <Route index element={<ClanZakonaDetail />} />
      <Route path="edit" element={<ClanZakonaUpdate />} />
      <Route path="delete" element={<ClanZakonaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ClanZakonaRoutes;
