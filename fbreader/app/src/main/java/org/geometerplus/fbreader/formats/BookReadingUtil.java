/*
 * Copyright (C) 2012-2015 FBReader.ORG Limited <contact@fbreader.org>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.geometerplus.fbreader.formats;

import java.io.IOException;

import org.amse.ys.zip.ZipException;

import org.geometerplus.zlibrary.core.filesystem.ZLFile;
import org.geometerplus.zlibrary.core.resources.ZLResource;

public final class BookReadingUtil {
	private static String getResourceText(String resourceId, String ... params) {
		String message = ZLResource.resource("errorMessage").getResource(resourceId).getValue();
		for (String p : params) {
			message = message.replaceFirst("%s", p);
		}
		return message;
	}

	public static void throwException(String resourceId, ZLFile file, String[] params) throws BookReadingException{
		throw new BookReadingException(getResourceText(resourceId, params), file);
	}

	public static void throwException(String resourceId, ZLFile file) throws BookReadingException {
		throwException(resourceId, file, new String[] { file.getPath() });
	}

	public static void throwException(IOException e, ZLFile file) throws BookReadingException {
		throw new BookReadingException(getResourceText(
			e instanceof ZipException ? "errorReadingZip" : "errorReadingFile",
			file.getPath()
		), e, file);
	}
}