import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Osoba from './osoba';
import OsobaDetail from './osoba-detail';
import OsobaUpdate from './osoba-update';
import OsobaDeleteDialog from './osoba-delete-dialog';

const OsobaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Osoba />} />
    <Route path="new" element={<OsobaUpdate />} />
    <Route path=":id">
      <Route index element={<OsobaDetail />} />
      <Route path="edit" element={<OsobaUpdate />} />
      <Route path="delete" element={<OsobaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default OsobaRoutes;
