package com.kreative.bitsnpicas.edit;

import com.kreative.bitsnpicas.Font;

public interface GlyphListListener {
	public void codePointsSelected(GlyphList gl, Font<?> font);
	public void codePointsOpened(GlyphList gl, Font<?> font);
	public void metricsChanged(GlyphList gl, Font<?> font);
	public void glyphsChanged(GlyphList gl, Font<?> font);
}
