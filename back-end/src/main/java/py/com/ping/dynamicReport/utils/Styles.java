/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.dynamicReport.utils;

import java.awt.Color;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;

/**
 *
 * @author rudy
 */
public class Styles {

    public static final StyleBuilder boldStyle = stl.style().bold();
    public static final StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER);
    public static StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle)
	                                          .setBorder(stl.pen1Point())
	                                          .setBackgroundColor(Color.LIGHT_GRAY);
}
