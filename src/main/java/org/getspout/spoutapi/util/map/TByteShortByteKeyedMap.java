/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.util.map;

/**
 * A simplistic map that supports (byte, short, byte) keys, using a trove int * hashmap in the backend.
 *
 */
public class TByteShortByteKeyedMap {
	public static final int key(int x, int y, int z) {
		return  ((x & 0xFF) << 24) | ((z & 0xFF) << 16) | (y & 0xFFFF);
	}

	public static byte getXFromKey(int key) {
		return (byte)(key >> 24);
	}

	public static short getYFromKey(int key) {
		return (short)key;
	}

	public static byte getZFromKey(int key) {
		return (byte)(key >> 16);
	}
}
