import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Kazna from './kazna';
import KaznaDetail from './kazna-detail';
import KaznaUpdate from './kazna-update';
import KaznaDeleteDialog from './kazna-delete-dialog';

const KaznaRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Kazna />} />
    <Route path="new" element={<KaznaUpdate />} />
    <Route path=":id">
      <Route index element={<KaznaDetail />} />
      <Route path="edit" element={<KaznaUpdate />} />
      <Route path="delete" element={<KaznaDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default KaznaRoutes;
