/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.infrastructure.docker.client.params;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

/** @author Mykola Morhun */
public class StartExecParamsTest {
  private static final String EXEC_ID = "exec_id";
  private static final Boolean DETACH = Boolean.FALSE;
  private static final Boolean TTY = Boolean.TRUE;

  private StartExecParams startExecParams;

  @Test
  public void shouldCreateParamsObjectWithRequiredParameters() {
    startExecParams = StartExecParams.create(EXEC_ID);

    assertEquals(startExecParams.getExecId(), EXEC_ID);

    assertNull(startExecParams.isDetach());
    assertNull(startExecParams.isTty());
  }

  @Test
  public void shouldCreateParamsObjectWithAllPossibleParameters() {
    startExecParams = StartExecParams.create(EXEC_ID).withDetach(DETACH).withTty(TTY);

    assertEquals(startExecParams.getExecId(), EXEC_ID);
    assertEquals(startExecParams.isDetach(), DETACH);
    assertEquals(startExecParams.isTty(), TTY);
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void shouldThrowNullPointerExceptionIfExecIdRequiredParameterIsNull() {
    startExecParams = StartExecParams.create(null);
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void shouldThrowNullPointerExceptionIfExecIdRequiredParameterResetWithNull() {
    startExecParams = StartExecParams.create(EXEC_ID).withExecId(null);
  }

  @Test
  public void detachParameterShouldEqualsNullIfItNotSet() {
    startExecParams = StartExecParams.create(EXEC_ID).withTty(TTY);

    assertNull(startExecParams.isDetach());
  }

  @Test
  public void ttyParameterShouldEqualsNullIfItNotSet() {
    startExecParams = StartExecParams.create(EXEC_ID).withDetach(DETACH);

    assertNull(startExecParams.isTty());
  }
}
