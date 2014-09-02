/**
 * <copyright>
 * 
 * Copyright (c) 2010-2014 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.ui.log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Logger;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;


/**
 * A logger dedicated to Diff/Merge logging.
 * @author Olivier Constant
 */
@SuppressWarnings("nls")
public class DiffMergeLogger implements Logger {
  
  /** The line separator (OS-dependent) */
  public static final String LINE_SEP = System.getProperty("line.separator");
  
  /** The file where to log */
  private static final String LOG_FILE_NAME = "ModelComparisonLog.txt";
  
  /** The indent per indent level */
  private static final String INDENT = "  ";
  
  /** The non-null path for the log file */
  private final IPath _logFile;
  
  /** The (initially null) writer to the log file */
  private Writer _writer;
  
  
  /**
   * Constructor
   */
  public DiffMergeLogger() {
    _logFile = Platform.getLogFileLocation().removeLastSegments(1).append(
        LOG_FILE_NAME);
    _writer = null;
  }
  
  /**
   * Append to the given string builder the given string so that it appears
   * at the given level of indent.
   * @param builder_p a non-null string builder
   * @param level_p a strictly positive int or 0
   * @param addition_p a non-null string
   */
  public static void appendAtLevel(StringBuilder builder_p, int level_p, String addition_p) {
    for (int i=0; i < level_p; i++) {
      builder_p.append(INDENT);
    }
    builder_p.append(addition_p);
    builder_p.append(LINE_SEP);
  }
  
  /**
   * Close the logger
   */
  public void close() {
    if (_writer != null) {
      try {
      _writer.close();
      } catch (IOException e) {
        // Ignore
      }
    }
  }
  
  /**
   * Return the header for the log file
   * @return a non-null string
   */
  protected String getHeader() {
    return "****** Diff/Merge Report ******" + LINE_SEP;
  }
  
  /**
   * Return the path for the log file
   * @return a non-null path
   */
  public IPath getLogFile() {
    return _logFile;
  }
  
  /**
   * Try and return the writer for logging
   * @return a potentially null writer
   */
  protected Writer getWriter() {
    if (_writer == null) {
      String file = _logFile.toOSString();
      try {
        FileOutputStream os = new FileOutputStream(file, false);
        _writer = new OutputStreamWriter(os);
        _writer.append(getHeader());
      } catch (IOException e) {
        EMFDiffMergeUIPlugin.getDefault().getLog().log(
            new Status(IStatus.WARNING, EMFDiffMergeUIPlugin.getDefault().getPluginId(),
                "Cannot log Diff/Merge events", e));
      }
    }
    return _writer;
  }
  
  /**
   * @see org.eclipse.emf.common.util.Logger#log(java.lang.Object)
   */
  public void log(Object logEntry_p) {
    if (logEntry_p instanceof AbstractLogEvent) {
      @SuppressWarnings("resource") // Closed when plug-in is stopped
      Writer writer = getWriter();
      if (writer != null) {
        AbstractLogEvent logEvent = (AbstractLogEvent)logEntry_p;
        try {
          writer.write(logEvent.getRepresentation());
          writer.flush();
        } catch (IOException e) {
          EMFDiffMergeUIPlugin.getDefault().getLog().log(
              new Status(IStatus.WARNING, EMFDiffMergeUIPlugin.getDefault().getPluginId(),
                  "Cannot log Diff/Merge event: " + logEntry_p, e));
        }
      }
    }
  }
  
}
